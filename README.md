# casei_sample_v1

## When to Compose Use Cases

- When you need the full business logic of another use case.
  - **Example:** `ApproveOrderUseCase` calling `ValidatePaymentUseCase` (both have their own business rules).

## When to Use Gateway Directly

- Simple CRUD operations (find, save, delete).
- Validation checks.
- Data retrieval without complex business logic.
