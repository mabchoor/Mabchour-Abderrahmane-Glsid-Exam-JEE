export interface Client {
    id?: number;
    nom: string;
    prenom: string;
    email: string;
    telephone: string;
    adresse: string;
    dateNaissance: Date;
    profession: string;
    revenuMensuel: number;
    credits?: any[]; // Will be populated with Credit objects
} 