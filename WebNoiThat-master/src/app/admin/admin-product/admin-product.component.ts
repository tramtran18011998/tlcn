import { Component, OnInit ,ViewChild} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Product } from 'src/app/corecontrol/models/product';
import { ProductService } from 'src/app/corecontrol/services/product.service';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent implements OnInit {

  displayedColumns: string[] = ['id','name', 'color', 'price','discountPrice','material','quantity','size','choose'];
  
  

  dataSource ;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  private products: Product[]=[];

  constructor(private productService: ProductService,private router: Router) { }

  ngOnInit() {
    this.getList();
  }

  getList(){
    this.productService.getList().subscribe(data =>{
      console.log(data);
      this.products = data;
      this.dataSource = new MatTableDataSource(this.products);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
    
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  delete(id: number){   
    Swal.fire({
      title: 'Bạn có chắc chắn muốn xóa không?',
      text: 'Dữ liệu sẽ không thể phục hồi!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Đồng ý xóa!'
    }).then((result) => {
      if (result.value) {
        this.productService.delete(id).subscribe(data =>{
          console.log(data);
          this.getList();
        });
        Swal.fire(
          'Đã xóa!',
          'Dữ liệu đã xóa.',
          'success'
        );
      }
    });
    
  }
  edit(id: number){    
    this.router.navigate(['/admin/adproduct/edit',id]);
  }
  detail(id: number){    
    this.router.navigate(['/admin/adproduct/detail',id]);
  }

}
