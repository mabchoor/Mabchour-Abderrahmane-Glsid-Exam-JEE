import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client.interface';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  selectedClient: Client | null = null;
  isEditing = false;
  loading = false;
  error = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.loading = true;
    this.clientService.getAllClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error loading clients';
        this.loading = false;
        console.error('Error loading clients:', err);
      }
    });
  }

  selectClient(client: Client): void {
    this.selectedClient = { ...client };
    this.isEditing = true;
  }

  createClient(): void {
    this.selectedClient = {
      nom: '',
      prenom: '',
      email: '',
      telephone: '',
      adresse: '',
      dateNaissance: new Date(),
      profession: '',
      revenuMensuel: 0
    };
    this.isEditing = false;
  }

  saveClient(): void {
    if (!this.selectedClient) return;

    const operation = this.isEditing
      ? this.clientService.updateClient(this.selectedClient.id!, this.selectedClient)
      : this.clientService.createClient(this.selectedClient);

    operation.subscribe({
      next: () => {
        this.loadClients();
        this.selectedClient = null;
      },
      error: (err) => {
        this.error = 'Error saving client';
        console.error('Error saving client:', err);
      }
    });
  }

  deleteClient(id: number): void {
    if (confirm('Are you sure you want to delete this client?')) {
      this.clientService.deleteClient(id).subscribe({
        next: () => {
          this.loadClients();
        },
        error: (err) => {
          this.error = 'Error deleting client';
          console.error('Error deleting client:', err);
        }
      });
    }
  }

  cancel(): void {
    this.selectedClient = null;
  }
}
