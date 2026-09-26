"use client";
import { useEffect } from "react";

// Adds a Copy button to every code block in the lesson.
export function CopyButtons({ containerId }: { containerId: string }) {
  useEffect(() => {
    const root = document.getElementById(containerId);
    if (!root) return;
    const buttons: HTMLButtonElement[] = [];
    root.querySelectorAll<HTMLElement>(".code").forEach((wrap) => {
      const pre = wrap.querySelector("pre");
      if (!pre || wrap.querySelector(".copy")) return;
      const button = document.createElement("button");
      button.type = "button";
      button.className = "copy";
      button.textContent = "Copy";
      const done = (text: string) => {
        button.textContent = text;
        setTimeout(() => (button.textContent = "Copy"), 1600);
      };
      button.addEventListener("click", () => {
        navigator.clipboard?.writeText(pre.innerText).then(
          () => done("Copied!"),
          () => {
            const range = document.createRange();
            range.selectNodeContents(pre);
            getSelection()?.removeAllRanges();
            getSelection()?.addRange(range);
            done("Press Ctrl+C");
          },
        );
      });
      wrap.appendChild(button);
      buttons.push(button);
    });
    return () => buttons.forEach((b) => b.remove());
  }, [containerId]);
  return null;
}
