# Feature 02 — Request validation with DTOs

Branch: `feature/JS-02-validation` (off your working branch)

---

## Theory

### Why validate at the DTO, not deep in the code

A request arrives as JSON. Spring deserializes it into your `CreateProductCommand` record.
**Before** any service or repository touches that data, you want to reject anything nonsensical —
a blank name, a negative price, a percentage of 500. The earliest sensible place to do that is
the **boundary**: the request DTO. Catch bad input the moment it enters the application, and the
rest of your code can assume the data is already sane.

This is different from **domain invariants** — the `Validate.notBlank(...)` calls still sitting
in your `Electronics` constructor. Those protect the *object* from ever existing in a broken
state, no matter who creates it. The two layers are not redundant in principle:

- **DTO validation** = "was the *request* well-formed?" → answered with HTTP 400.
- **Domain invariant** = "can this *object* legally exist?" → a programming safety net.

For this feature the important one is DTO validation. We'll leave the domain constructors alone.

### Jakarta Bean Validation

Java has a standard for declarative validation: **Jakarta Bean Validation** (the annotations
`@NotBlank`, `@NotNull`, `@Positive`, `@Min`, `@Max`, `@Future`, `@Size`, …). You put an
annotation on a field and a validator engine (Hibernate Validator, pulled in by
`spring-boot-starter-validation`) checks it. You declare *what* must be true; you don't write
the *how*.

Annotations you'll likely need here, and the distinction that trips people up:

- `@NotNull` — the value must not be null. Says nothing about blank strings.
- `@NotBlank` — for **strings**: not null, and not just whitespace.
- `@Positive` / `@PositiveOrZero` — for numbers.
- `@Min(x)` / `@Max(x)` — numeric bounds.
- `@Future` / `@FutureOrPresent` — for dates/times.
- `@DecimalMin("0.00")` — for `BigDecimal` bounds.

### The `@Valid` trigger

Annotations on the DTO do **nothing on their own**. They only run when you ask Spring to
validate — by putting `@Valid` in front of the `@RequestBody` parameter in the controller.
When validation fails, Spring throws `MethodArgumentNotValidException`. You already wrote a
handler for that exception, so the failure becomes a **400** automatically. That's the whole
chain: `@Valid` → exception → your handler → 400.

(`@Valid` is the standard Jakarta annotation. There's also Spring's `@Validated`, which adds
validation *groups* and works on other layers — you don't need it yet. Use `@Valid` on the body.)

### The primitive trap

`quantity` is an `int`. A primitive can never be null, so if the JSON omits `quantity`
entirely, it deserializes to **0**, not an error. That's why `@NotNull` is meaningless on an
`int` — but `@Positive` still catches the 0. Keep this in mind when choosing annotations:
object types (`String`, `BigDecimal`, `Integer`) can be null; primitives can't.

---

## Goal

Every request body that creates or updates something is validated at the controller boundary,
and bad input comes back as a clean **400** with a message that tells the caller *which field*
was wrong.

---

## Steps

### Step 1 — Constrain `CreateProductCommand`

Add constraints to each record component. Decide, for each field, what "valid" means and pick
the matching annotation:

- `id` — must be present and not blank. Which annotation, given it's a `String`?
- `name` — same reasoning.
- `price` — a `BigDecimal`. It must be present **and** greater than zero. That's often *two*
  annotations, not one. Which two?
- `quantity` — an `int`. Re-read the "primitive trap" above and pick the one annotation that
  actually does something here.

Hint on syntax: annotations go directly before the component inside the record header, e.g.
`public record CreateProductCommand(@NotBlank String id, ...)`. Import them from
`jakarta.validation.constraints`.

### Step 2 — Constrain `CreateVoucherCommand`

- `voucherName` — present, not blank.
- `expirationDate` — a `LocalDate`. It must be present, and it can't be in the past (a voucher
  that's already expired is pointless to create). Two annotations.
- `percentage` — an `int`. A discount percentage has a sensible range. What's the lowest
  meaningful value, and the highest? Use bound annotations.

### Step 3 — Turn validation on in the controllers

Add `@Valid` to the `@RequestBody` parameters. Cover:

- `ProductController.create`
- `ProductController.update`
- `VoucherController.create`

Nothing else changes in the controllers — no try/catch, no manual checks. The handler you
already wrote does the rest.

### Step 4 — Make the 400 body readable (improve the handler)

Right now your `MethodArgumentNotValidException` handler returns `e.getMessage()`, which is a
long, ugly dump. Improve it so the response lists the offending fields.

Guidance (not the full code): the exception gives you the binding result. From
`e.getBindingResult().getFieldErrors()` you get a list where each item has `getField()` and
`getDefaultMessage()`. Build a compact string (or, if you want, extend `ErrorMessageDto` to
carry a `Map<String,String>` of field → message). Keep the HTTP status at 400.

Think about: do you want one combined message, or a structured map the frontend can read
field-by-field? Either is fine — pick one and justify it to yourself.

### Step 5 — (Optional) custom messages

Any constraint takes a `message`, e.g. `@Positive(message = "price must be greater than zero")`.
Add messages where the default ("must be greater than 0") isn't clear enough. Don't overdo it.

### Out of scope (don't do these now)

- Don't remove the `Validate.*` calls from the domain constructors — that's a separate cleanup.
- Don't try to make `CreateProductCommand` handle Computer/SmartPhone-specific fields
  (cpu, battery, …). The create endpoint only builds a base `Electronics` today; extending it
  is a future feature.

---

## Testing checklist (Bruno)

**Remember the header on every request: `X-API-KEY: let-me-in`** (the API filter returns 401
without it — don't mistake that for a validation result).

Products:

| # | Request | Body | Expect |
|---|---------|------|--------|
| 1 | POST `/api/products` | valid product (non-blank id/name, price > 0, quantity > 0) | 201 |
| 2 | POST `/api/products` | blank `name` | 400, message names `name` |
| 3 | POST `/api/products` | `price` = -5 | 400, message names `price` |
| 4 | POST `/api/products` | omit `quantity` entirely | 400 (it defaults to 0 → fails `@Positive`) |
| 5 | POST `/api/products` | omit `price` entirely | 400 (null → fails the not-null rule) |
| 6 | POST `/api/products` | `{ "name": "x", "price": }` (broken JSON) | 400 via the *JSON* handler, not the validation one |
| 7 | PUT `/api/products/{id}` | blank `name` | 400 |

Vouchers:

| # | Request | Body | Expect |
|---|---------|------|--------|
| 8 | POST `/api/vouchers` | `expirationDate` in the past | 400 |
| 9 | POST `/api/vouchers` | `percentage` = 0 (or > 100) | 400 |
| 10 | POST `/api/vouchers` | valid voucher | 201 |

For requests 2–5 and 8–9, **look at the response body** — after Step 4 it should tell you the
field name and why it failed. That readability is the point.

---

## Definition of done

- [ ] Both create-commands carry constraints
- [ ] `@Valid` on the three controller methods
- [ ] Handler returns a field-aware 400 body
- [ ] All 10 Bruno requests pass with the expected statuses
- [ ] Branch pushed, PR opened

---

## Traps

- Forgetting `@Valid` — the annotations sit there silently and everything returns 201. If a bad
  body isn't rejected, this is almost always why.
- Using `@NotNull` on `String` and wondering why `""` passes — you want `@NotBlank`.
- Using `@NotBlank` on a number — it's string-only; won't compile / won't apply. Use `@NotNull`
  + a range annotation for numbers.
- Expecting request #6 (broken JSON) to hit the validation handler — it can't, because the body
  never deserializes far enough to validate. It's caught by your `HttpMessageNotReadableException`
  handler instead. Knowing *which* handler fires is the lesson.
