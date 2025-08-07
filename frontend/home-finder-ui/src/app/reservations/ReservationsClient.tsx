'use client';

import { toast } from 'react-hot-toast';
import axios from 'axios';
import { useCallback, useState } from 'react';
import { useRouter } from 'next/navigation';

import { SafeReservations, SafeUser } from '../types';
import Heading from '@/components/Heading';
import Container from '@/components/Container';
import ListingCard from '@/components/listings/ListingCard';

interface ReservationsClientProps {
    reservations: SafeReservations[],
    currentUser?: SafeUser | null;
}

const ReservationsClient: React.FC<ReservationsClientProps> = ({
    reservations,
    currentUser
}) => {
    const router = useRouter();
    const [deletingId, setDeletingId] = useState('');

    const onCancel = useCallback((id : string) => {
        setDeletingId(id);

        axios.delete(`/api/reservations/${id}`)
        .then(() => {
            toast.success('Reservation cancelled');
            router.refresh();
        })
        .catch((error) => {
            toast.error('Something went wrong.');
        })
        .finally(() => {
            setDeletingId('');
        });
    }, [router]);

    return (  
        <div className='mt-15'>
            <Container>
                <Heading
                    title="Reservations"
                    subtitle="Booking on your properties"
                />
                <div
                    className='
                        mt-10
                        grid
                        grid-cols-1
                        sm:grid-cols-2
                        md:grid-col-3
                        lg:grid-cols-4
                        xl:grid-cols-5
                        2xl:grid-cols-6
                        gap-8
                    '
                >
                    {reservations.map((reservation) => (
                        <ListingCard
                            key={reservation.id}
                            data={reservation.listing}
                            reservation={reservation}
                            actionId={reservation.id}
                            onAction={onCancel}
                            disabled={deletingId === reservation.id}
                            actionLabel='Cancel guest reservation'
                            currentUser={currentUser}
                        />
                    ))}
                </div>
            </Container>
        </div>
    );
}
 
export default ReservationsClient;