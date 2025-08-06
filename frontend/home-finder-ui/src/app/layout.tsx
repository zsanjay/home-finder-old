import type { Metadata } from "next";
import './globals.css';
import ReactQueryProvider from '@/components/ReactQueryProvider'
import { Nunito } from "next/font/google";
import Navbar from "@/components/navbar/Navbar";
import ClientOnly from "@/components/ClientOnly";
import RegisterModal from "@/components/modals/RegisterModal";
import ToasterProvider from "./providers/ToasterProvider";
import LoginModal from "@/components/modals/LoginModal";
import getCurrentUser from "./actions/getCurrentUser";
import RentModel from "@/components/modals/RentModal";

export const metadata: Metadata = {
  title: "Home Finder",
  description: "Home Finder App",
};

const font = Nunito({
  subsets: ["latin"]
})

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const currentUser = await getCurrentUser();
  return (
    <html lang="en">
      <body
        className={font.className}
      >
        <ClientOnly>
          <ToasterProvider />
          <RegisterModal />
          <LoginModal />
          <RentModel />
          <Navbar currentUser={currentUser}/>
        </ClientOnly>
        <div className="pb-20 pt-28">
          {children}
        </div>
        <ReactQueryProvider>
        </ReactQueryProvider>
      </body>
    </html>
  );
}
