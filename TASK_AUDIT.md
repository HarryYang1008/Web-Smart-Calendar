# Codebase Issue Triage and Proposed Tasks

## 1) Typo fix task
**Issue found:** The OpenAI system prompt text contains multiple typos and grammar issues such as "sended" and "conversition".

- Evidence: `src/ChatbotWindow.js` contains the phrase "my schedule will be sended" and "do the normal conversition" in the system prompt string.

**Proposed task:**
- Rewrite the system prompt in `src/ChatbotWindow.js` for clear, correct English while preserving current intent.
- Keep behavior unchanged; only improve wording quality and maintainability.
- Add a small constant for the prompt text to avoid long inline literals.

---

## 2) Bug fix task
**Issue found:** ICS export may generate malformed `DTEND` timestamps for single-digit end hours because hour padding is missing.

- Evidence: In `src/map.jsx`, `generateICS` computes `DTEND` using `parseInt(hour) + 1` directly in a template string, which can produce values like `T93000Z` instead of `T093000Z`.

**Proposed task:**
- Update `generateICS` in `src/map.jsx` to left-pad computed hour values to 2 digits.
- Add rollover handling for `23:xx` events (either clamp, wrap with day increment, or document expected behavior).
- Add unit coverage for ICS string generation edge cases (`08:30`, `09:30`, `23:30`).

---

## 3) Code comment/documentation discrepancy task
**Issue found:** Repository documentation is still the default Create React App template and does not describe this calendar/chatbot app, so docs do not match current codebase behavior.

- Evidence: `README.md` only contains boilerplate CRA instructions while the app includes custom calendar + chatbot logic in `src/map.jsx` and `src/ChatbotWindow.js`.

**Proposed task:**
- Replace README boilerplate with project-specific documentation:
  - App purpose and main features.
  - Environment variables (e.g., `REACT_APP_OPENAI_API_KEY`).
  - How to run and test.
  - Known limitations (timezone handling, ICS assumptions).

---

## 4) Test improvement task
**Issue found:** The existing test is leftover CRA scaffolding and checks for "learn react", which the app no longer renders.

- Evidence: `src/App.test.js` asserts `getByText(/learn react/i)` while `src/App.js` only renders `<Map />`.

**Proposed task:**
- Replace `App.test.js` with behavior-focused tests aligned to current UI:
  - Verify calendar header renders.
  - Verify toggling between monthly/weekly views.
  - Verify opening the add-event modal from a date cell.
- Mock browser APIs used by the component where needed.
