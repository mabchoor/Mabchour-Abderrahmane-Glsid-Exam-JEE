import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Remboursement } from '../models/credit.interface';

@Injectable({
  providedIn: 'root'
})
export class RepaymentService {
  private apiUrl = 'http://localhost:8088/api/remboursements';

  constructor(private http: HttpClient) { }

  getAllRepayments(): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(this.apiUrl);
  }

  getRepaymentById(id: number): Observable<Remboursement> {
    return this.http.get<Remboursement>(`${this.apiUrl}/${id}`);
  }

  createRepayment(repayment: Remboursement): Observable<Remboursement> {
    return this.http.post<Remboursement>(this.apiUrl, repayment);
  }

  updateRepayment(id: number, repayment: Remboursement): Observable<Remboursement> {
    return this.http.put<Remboursement>(`${this.apiUrl}/${id}`, repayment);
  }

  deleteRepayment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getRepaymentsByCreditId(creditId: number): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(`${this.apiUrl}/credit/${creditId}`);
  }
}
