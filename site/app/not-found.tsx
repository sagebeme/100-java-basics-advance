import Link from "next/link";
import { Footer, Navbar } from "@/components/Chrome";

export default function NotFound() {
  return (
    <>
      <Navbar />
      <main className="container" style={{ padding: "96px 0", textAlign: "center" }}>
        <p className="label">404</p>
        <h1 style={{ fontSize: 40, margin: "16px 0" }}>That page isn&apos;t here</h1>
        <p><Link href="/">Back to the start</Link> or <Link href="/syllabus/">see all 100 days</Link>.</p>
      </main>
      <Footer />
    </>
  );
}
