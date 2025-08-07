import Container from "@/components/Container";
import { SafeListings, SafeUser } from "../types";
import Heading from "@/components/Heading";
import ListingCard from "@/components/listings/ListingCard";

interface FavoritesClientProps {
    listings: SafeListings[],
    currentUser?: SafeUser | null;
}

const FavoritesClient: React.FC<FavoritesClientProps> = ({
    listings,
    currentUser
}) => {
    return (
        <div className="mt-15">
            <Container>
                <Heading 
                    title="Favorites"
                    subtitle="List of places you have favorited!"
                />
                <div
                    className="
                        mt-10
                        grid
                        grid-col-1
                        sm:grid-cols-2
                        md:grid-cols-3
                        lg:grid-cols-4
                        xl:grid-cols-5
                        2xl:grid-cols-6
                        gap-8
                    "
                >
                    {listings.map((listing) => (
                        <ListingCard 
                            currentUser={currentUser}
                            key={listing.id}
                            data={listing}
                        />
                    ))}
                </div>
            </Container>
        </div>
      );
}
 
export default FavoritesClient;