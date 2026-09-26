import Link from "next/link";
import { REPO } from "@/lib/course";

// The navbar, the Fork-me ribbon and the footer: the same on every page.
export function Navbar({ current }: { current?: "path" | "syllabus" }) {
  return (
    <>
      <nav className="navbar" aria-label="Main">
        <div className="container">
          <Link className="brand" href="/">
            <span className="logo" aria-hidden="true">☕</span>
            <span className="word">100 Days of Java</span>
            <span className="sr-only">: home</span>
          </Link>
          <ul className="nav-links">
            <li><Link href="/#demo">Why Java</Link></li>
            <li><Link href="/#path" aria-current={current === "path" ? "page" : undefined}>The path</Link></li>
            <li className="keep"><Link href="/syllabus/" aria-current={current === "syllabus" ? "page" : undefined}>Syllabus</Link></li>
          </ul>
        </div>
      </nav>
      <div className="ribbon"><a href={REPO}>Fork me on GitHub</a></div>
    </>
  );
}

export function Footer({ full = false }: { full?: boolean }) {
  return (
    <footer className="footer">
      <div className="container">
        {full && (
          <div className="cols">
            <div>
              <h4>100 Days of Java</h4>
              <p>One lesson a day for 100 days: plain Java first, then JavaFX, APIs and automation, Spring Boot, databases, and 20 portfolio projects.</p>
            </div>
            <div>
              <h4>Course</h4>
              <ul>
                <li><Link href="/syllabus/">Full syllabus</Link></li>
                <li><Link href="/day/1/">Day 1: Variables</Link></li>
                <li><Link href="/day/100/">Day 100: Machine Learning</Link></li>
              </ul>
            </div>
            <div>
              <h4>Code</h4>
              <ul>
                <li><a href={REPO}>Source on GitHub</a></li>
                <li><a href="https://adoptium.net/">Get JDK 21 (Temurin)</a></li>
                <li><a href="https://maven.apache.org/download.cgi">Get Maven</a></li>
              </ul>
            </div>
          </div>
        )}
        <div className="bottom" style={full ? undefined : { margin: 0, padding: 0, border: 0 }}>
          <span>Made with <span className="heart" aria-label="love">♥</span> and a lot of coffee.</span>
          <span>Progress is saved only in this browser.</span>
        </div>
      </div>
    </footer>
  );
}
