"use client";
import { useEffect, useState } from "react";

// The round "back to top" button that appears once you've scrolled a way down.
export function ToTop() {
  const [show, setShow] = useState(false);
  useEffect(() => {
    const update = () => setShow(window.scrollY > 700);
    update();
    window.addEventListener("scroll", update, { passive: true });
    return () => window.removeEventListener("scroll", update);
  }, []);
  return (
    <a className={`to-top${show ? " show" : ""}`} href="#top" aria-label="Back to top" tabIndex={show ? 0 : -1}>
      ↑
    </a>
  );
}
