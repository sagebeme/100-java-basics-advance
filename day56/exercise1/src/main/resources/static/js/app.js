// Served from /js/app.js. Marks today's line on the menu board when the page has loaded.
document.addEventListener("DOMContentLoaded", () => {
    const day = new Date().toLocaleDateString("en-KE", { weekday: "long" });
    const note = document.querySelector("[data-today]");
    if (note) {
        note.textContent = "Open today, " + day + ", from 7am.";
    }
});
