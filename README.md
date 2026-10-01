# CPT S 422 Checkstyle Metrics

This Maven project implements two custom Checkstyle checks for Deliverable 1. Both are in `src/main/java/Del1/` and report one count per Java file.

| Check | Definition and assumptions |
| --- | --- |
| `CommentCountCheck` | A `//` comment counts once. A `/* ... */` block or Javadoc comment counts once, even when it spans multiple lines. Comments containing code-like text are still comments. This is a count of comments, not lines of comments. |
| `LoopCountCheck` | Each `for` (including enhanced `for`), `while`, or `do-while` statement counts once. Nested loops count separately. Loop-like text inside comments does not count. |
