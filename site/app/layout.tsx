import type { Metadata, Viewport } from "next";
import { Abril_Fatface, Caveat, Montserrat, Open_Sans, Pacifico, Source_Code_Pro } from "next/font/google";
import "highlight.js/styles/monokai-sublime.css";
import "./globals.css";
import { ToTop } from "@/components/ToTop";

// Fonts are downloaded at build time and served from the site itself: no request to Google.
const montserrat = Montserrat({ subsets: ["latin"], weight: ["400", "700", "800"], variable: "--font-montserrat" });
const openSans = Open_Sans({ subsets: ["latin"], weight: ["400", "600", "700"], style: ["normal", "italic"], variable: "--font-open-sans" });
const pacifico = Pacifico({ subsets: ["latin"], weight: "400", variable: "--font-pacifico" });
const code = Source_Code_Pro({ subsets: ["latin"], weight: ["400", "600"], variable: "--font-code" });
const display = Abril_Fatface({ subsets: ["latin"], weight: "400", variable: "--font-display" }); // the letterpress title
const chalk = Caveat({ subsets: ["latin"], weight: ["500", "700"], variable: "--font-chalk" }); // the chalkboard menu

export const metadata: Metadata = {
  title: { default: "100 Days of Java", template: "%s · 100 Days of Java" },
  description: "100 days of Java, from your first variable to Spring Boot web apps and a machine learning capstone. Most days have tests.",
  icons: { icon: "/favicon.svg" },
};

export const viewport: Viewport = { themeColor: "#2b1d14" };

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en" className={`${montserrat.variable} ${openSans.variable} ${pacifico.variable} ${code.variable} ${display.variable} ${chalk.variable}`}>
      <body id="top">
        {children}
        <ToTop />
      </body>
    </html>
  );
}
