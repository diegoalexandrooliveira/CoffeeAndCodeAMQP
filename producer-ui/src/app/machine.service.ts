import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Machine } from './machine.model';

@Injectable({ providedIn: 'root' })
export class MachineService {

    private api: string = "http://localhost:8080/api/machines";

    constructor(private httpClient: HttpClient) { }

    public create(machine: Machine): Observable<Machine> {
        return this.httpClient.post<Machine>(this.api, machine);
    }
}