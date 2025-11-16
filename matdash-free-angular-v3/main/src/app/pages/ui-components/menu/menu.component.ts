import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogRef, MatDialogModule } from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { FormsModule } from '@angular/forms';
import { jwtDecode } from 'jwt-decode';

import { Category } from 'src/app/providers/models/category.model';
import { AuthService } from 'src/app/providers/services/auth/auth.service';
import { CategoryService } from 'src/app/providers/services/category/category.service';

@Component({
  selector: 'app-categories',
  standalone: true,
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css'],
  imports: [
    CommonModule,
    FormsModule,

    // MATERIAL
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class AppMenuComponent implements OnInit {

  @ViewChild('dialogTemplate') dialogTemplate: any;
  dialogRef!: MatDialogRef<any>;

  categories: Category[] = [];
  userId!: number;

  form = {
    name: '',
    icon: '',
    color: '#000000'
  };

  displayedColumns = ['id', 'name', 'icon', 'color', 'createdAt', 'actions'];

  constructor(
    private categoryService: CategoryService,
    private authService: AuthService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadUserId();
    this.loadCategories();
  }

  loadUserId(): void {
    const token = this.authService.getToken();
    if (!token) return;

    const decoded: any = jwtDecode(token);
    this.userId = Number(decoded.id || decoded.sub);
  }

  loadCategories(): void {
    this.categoryService.getCategoriesByUser$(this.userId).subscribe({
      next: (list) => (this.categories = list)
    });
  }

  openDialog(): void {
    this.form = { name: '', icon: '', color: '#000000' };
    this.dialogRef = this.dialog.open(this.dialogTemplate);
  }

  saveCategory(): void {
    const payload = {
      ...this.form,
      userId: this.userId
    };

    this.categoryService.createCategory$(payload).subscribe({
      next: () => {
        this.loadCategories();
        this.dialogRef.close();
      }
    });
  }

  deleteCategory(id: number): void {
    if (!confirm('¿Eliminar esta categoría?')) return;

    this.categoryService.deleteCategory$(id).subscribe({
      next: () => this.loadCategories()
    });
  }
}
