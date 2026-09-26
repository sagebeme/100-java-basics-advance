import type { NextConfig } from "next";

// A fully static site: every page, including all 100 lessons, is built into out/ ahead of time.
const config: NextConfig = {
  output: "export",
  trailingSlash: true, // /day/7/ becomes day/7/index.html, which every static host serves
  images: { unoptimized: true },
};

export default config;
