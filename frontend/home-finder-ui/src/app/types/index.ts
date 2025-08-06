import { User } from "next-auth";
import { Listing } from "../../../generated/prisma";

export type SafeListings = Omit<
    Listing,
    "createdAt"
> & {
    createdAt : string;
};

export type SafeUser = Omit<
    User,
    "createdAt" | "updatedAt" | "emailVerified"
> & {
    createdAt : string;
    updatedAt : string;
    emailVerified : string | null;
    favoriteIds?: string[];
};

