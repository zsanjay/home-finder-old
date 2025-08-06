import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  eslint : {
    ignoreDuringBuilds : true
  },
  images: {
    // domains : [
    //   "avatars.githubusercontent.com",
    //   "lh3.googleusercontent.com"
    // ],
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'avatars.githubusercontent.com',
        pathname: '/**',
      },
      {
        protocol: 'https',
        hostname: 'lh3.googleusercontent.com',
        pathname: '/**',
      },
      {
        protocol: 'https',
        hostname: 'res.cloudinary.com',
        pathname: '/dcomislzr/image/upload/**',
      },
    ],
  }
};

export default nextConfig;
