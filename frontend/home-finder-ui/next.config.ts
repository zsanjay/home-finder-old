import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  eslint : {
    ignoreDuringBuilds : true
  },
  images: {
    domains : [
      "avatars.githubusercontent.com",
      "lh3.googleusercontent.com"
    ]
  }
};

export default nextConfig;
