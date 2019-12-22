import { Component, OnInit ,ViewChild} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Detail } from 'src/app/corecontrol/models/detail';
import { DetailService } from 'src/app/corecontrol/services/detail.service';

@Component({
  selector: 'app-admin-accessories',
  templateUrl: './admin-accessories.component.html',
  styleUrls: ['./admin-accessories.component.css']
})
export class AdminAccessoriesComponent implements OnInit {

  displayedColumns: string[] = ['id','name', 'color', 'price','discountPrice','material','quantity','choose'];

  dataSource ;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  private details: Detail[]=[];

  constructor(private detailService: DetailService, private router: Router) { }

  ngOnInit() {
    this.getList();
  }

  getList(){
    this.detailService.getList().subscribe(data =>{
      console.log(data);
      this.details = data;
      this.dataSource = new MatTableDataSource(this.details);
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
        this.detailService.delete(id).subscribe(data =>{
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
    this.router.navigate(['/admin/addetail/edit',id]);
  }
  detail(id: number){    
    this.router.navigate(['/admin/addetail/detail',id]);
  }

}
