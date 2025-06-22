export interface employee {
    names: string,
    surnames: string,
    age: number,
    role: string,
    salary: number,
    entryDate: string,
    departureDate?: string
    department: {
        id: number,
        name?: string,
        enabled?: string
    },
    enabled: string
}

export interface numberEmployees {  
    numberEmployees: number
}