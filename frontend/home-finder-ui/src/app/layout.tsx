import type { Metadata } from "next";
import './globals.css';
import { AppRouterCacheProvider } from '@mui/material-nextjs/v15-appRouter';
import { ThemeProvider } from '@mui/material/styles';
import theme from '../theme';
import ReactQueryProvider from '@/components/ReactQueryProvider'
import { Nunito } from "next/font/google";
import Navbar from "@/components/navbar/Navbar";
import ClientOnly from "@/components/ClientOnly";
import Modal from '../components/modals/Modal';

export const metadata: Metadata = {
  title: "Home Finder",
  description: "Home Finder App",
};

const font = Nunito({
  subsets: ["latin"]
})

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={font.className}
      >
        <ClientOnly>
          <Modal isOpen={false}/>
          <Navbar />
        </ClientOnly>
        <ReactQueryProvider>
        <AppRouterCacheProvider>
          <ThemeProvider theme={theme}>
            {children}
          </ThemeProvider>  
        </AppRouterCacheProvider>
        </ReactQueryProvider>
      </body>
    </html>
  );
}
