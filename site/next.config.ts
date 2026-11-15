import type { NextConfig } from "next";

// GitHub Pages serves the site under /<repo>/, so tools/build-pages.sh sets PAGES_BASE_PATH to
// that ("/100-java-basics-advance"). Netlify, Vercel and `npm run dev` leave it unset: the root.
const basePath = process.env.PAGES_BASE_PATH?.replace(/\/+$/, "") || undefined;

// A fully static site: every page, including all 100 lessons, is built into out/ ahead of time.
const config: NextConfig = {
  output: "export",
  basePath,
  trailingSlash: true, // /day/7/ becomes day/7/index.html, which every static host serves
  images: { unoptimized: true },
};

export default config;
