export interface Credit {
    id?: number;
    montant: number;
    tauxInteret: number;
    duree: number;
    dateDebut: Date;
    typeCredit: 'PERSONNEL' | 'IMMOBILIER' | 'PROFESSIONNEL';
    statut: 'EN_ATTENTE' | 'APPROUVE' | 'REJETE' | 'EN_COURS' | 'TERMINE';
    client: Client;
    remboursements?: Remboursement[];
}

export interface Remboursement {
    id?: number;
    montant: number;
    dateRemboursement: Date;
    credit: Credit;
} 