/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,

  images: {
    domains: ['images.unsplash.com'],
  },

  async rewrites() {
    return [
      {
        source: '/dev/api/:path*',
        destination: 'https://vegendigital.com/sistemas/mejunje/dev/api/:path*',
      },
      {
        source: '/lab',
        destination: 'https://kamelo.vercel.app/lab',
      },
      {
        source: '/lab/:path*',
        destination: 'https://kamelo.vercel.app/lab/:path*',
      },
    ];
  },
};

module.exports = nextConfig;
