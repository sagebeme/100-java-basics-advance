import type { Metadata } from "next";
import Link from "next/link";
import { STAGES, summaries } from "@/lib/course";
import { Footer, Navbar } from "@/components/Chrome";
import { SyllabusTable } from "@/components/SyllabusTable";

export const metadata: Metadata = {
  title: "Syllabus",
  description: "All 100 lessons of 100 Days of Java, stage by stage, and how to set up.",
};

export default function Syllabus() {
  return (
    <>
      <a className="skip" href="#main">Skip to content</a>
      <Navbar current="syllabus" />
      <header className="page-head">
        <div className="container">
          <ol className="crumbs"><li><Link href="/">Home</Link></li><li aria-current="page">Syllabus</li></ol>
          <h1>The full syllabus</h1>
          <p className="sub">100 lessons in 5 stages, from your first variable to Spring Boot and machine learning.</p>
        </div>
      </header>
      <main id="main" className="container">
        <SyllabusTable days={summaries()} stages={STAGES.map((s) => ({ ...s }))} />

        <section className="panel prose" id="setup" aria-labelledby="setup-title" style={{ margin: "8px 0 64px" }}>
          <h2 id="setup-title" style={{ marginTop: 0 }}>Set up once, in about fifteen minutes</h2>
          <ol>
            <li><strong>Install JDK 21</strong>, for example <a href="https://adoptium.net/">Eclipse Temurin</a>. Check with <code>java -version</code>. Days 1–53 also run on JDK 11+, but Days 54–100 need 21.</li>
            <li>
              <strong>Get the course:</strong>
              <div className="code"><pre><code>{"git clone https://github.com/sagebeme/100-java-basics-advance.git\ncd 100-java-basics-advance"}</code></pre></div>
            </li>
            <li><strong>Start Day 1:</strong> read <code>day01/README.md</code>, do the exercises in <code>exercise1/</code> and <code>exercise2/</code>, then build the project from <code>_start/</code>.</li>
            <li><strong>For the GUI days</strong> (18, 22–23, 27–31), install the <a href="https://gluonhq.com/products/javafx/">JavaFX SDK</a>. The later JavaFX projects get it from Maven automatically.</li>
            <li><strong>From Day 54</strong>, install <a href="https://maven.apache.org/download.cgi">Maven</a> 3.9+. Every day is then its own Maven project: <code>mvn test</code> and <code>mvn spring-boot:run</code>.</li>
          </ol>
          <p style={{ margin: 0 }}>Commit your work every day. In three months you&apos;ll want to see how far you came.</p>
        </section>
      </main>
      <Footer />
    </>
  );
}
