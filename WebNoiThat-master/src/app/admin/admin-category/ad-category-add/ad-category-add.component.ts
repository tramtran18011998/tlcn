import { Component, OnInit } from '@angular/core';
import { CategoryType } from 'src/app/corecontrol/models/categorytype';
import { CategoryTypeService } from 'src/app/corecontrol/services/category-type.service';
import { CategoryService } from 'src/app/corecontrol/services/category.service';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import Swal from 'sweetalert2'
import { Category } from 'src/app/corecontrol/models/category';
import { Router } from '@angular/router';


@Component({
  selector: 'app-ad-category-add',
  templateUrl: './ad-category-add.component.html',
  styleUrls: ['./ad-category-add.component.css']
})
export class AdCategoryAddComponent implements OnInit {

  categoryTypes: CategoryType[]=[];
  categoryTypesArray: CategoryType[]=[];

  category: Category = new Category();
  categoryType: CategoryType = new CategoryType();

  addForm: FormGroup;

  constructor(private router: Router,private categoryTypeService: CategoryTypeService, private categoryService: CategoryService,private formBuilder: FormBuilder) { }

  ngOnInit() {
    
    this.addForm = this.formBuilder.group({
      name: new FormControl(''),
      categoryTypesF: new FormArray([])
    });
    //this.addCheckboxes();
    this.getListCategoryType();
  }

  // private addCheckboxes() {
  //   this.getListCategoryType();
  //   this.categoryTypes.forEach((o, i) => {
  //     const control = new FormControl(i === 0); // if first item set to true, else false
  //     (this.addForm.controls.categoryTypesF as FormArray).push(control);
  //   });
  //   console.log("c: ",this.addForm.controls.categoryTypesF.value);
  // }


  getListCategoryType(){
    this.categoryTypeService.getList().subscribe(data => {
      
      this.categoryTypes = data;
      
      console.log(this.categoryTypes);

      //add checkbox true false value array
      this.categoryTypes.forEach((o, i) => {
        const control = new FormControl(i === 0); // if first item set to true, else false
        //console.log("d: ",control);
        (this.addForm.controls['categoryTypesF'] as FormArray).push(control);
      });
      //console.log("c: ",this.addForm.controls.categoryTypesF.value);
      
    })
  }

  getselect(){

    // const selectedOrderIds = this.addForm.value.categoryTypesF
    //   .map((v, i) => v ? this.categoryTypes[i].id : null)
    //   .filter(v => v !== null);
    // console.log(selectedOrderIds);
    // selectedOrderIds.forEach(element => {
    //   //console.log(element);
    //   this.categoryTypeService.getById(element).subscribe(data =>{
    //     console.log(data);
    //     this.categoryTypesArray.push(data);
    //     console.log("g:",this.categoryTypesArray);
    //   })
    //   //console.log("k2:",this.categoryTypesArray);
    // });
  }

  onSubmitCreate(addForm: FormGroup) {
  
    const selectedOrderIds = this.addForm.value.categoryTypesF
      .map((v, i) => v ? this.categoryTypes[i].id : null)
      .filter(v => v !== null);
    console.log(selectedOrderIds);
    // selectedOrderIds.forEach(element => {
    //   //console.log(element);
    //   this.categoryTypeService.getById(element).subscribe(data =>{
    //     console.log(data);
        
    //     this.categoryTypesArray.push(data);
    //     //const cates = this.categoryTypesArray;
    //     console.log("g:",this.categoryTypesArray);
    //   })
    //   //console.log("k2:",this.categoryTypesArray);
    // });
    //this.getselect();
    //console.log("dd",ca)
    for(let i=0; i< selectedOrderIds.length; i++){
      //console.log(selectedOrderIds[i]);
      this.categoryTypeService.getById(selectedOrderIds[i]).subscribe(data =>{
        console.log(data);       
        this.categoryTypesArray.push(data);
        console.log("g:",this.categoryTypesArray);
        if(this.categoryTypesArray.length == selectedOrderIds.length){
          console.log("x: ",this.categoryTypesArray);
          this.category.categoryTypes = this.categoryTypesArray;
          this.category.name = addForm.controls['name'].value;
          this.categoryService.createNew(this.category).subscribe(data => {
              console.log(data);
              Swal.fire(
                'Đã thêm!',
                'Dữ liệu đã được thêm thành công.',
                'success'
              );  
            })
            addForm.reset();
            this.router.navigate(['/admin/adcategory']);
        }
      })
    }
    
    
    console.log("k:",this.categoryTypesArray);

    //
    //this.categoryTypeService.getByName()
    //const selectedCountries = this.categoryTypes.filter( (categoryType) => categoryType.checked );
    // you could use an EventEmitter and emit the selected values here, or send them to another API with some service

    //console.log ("a: ",selectedCountries);
    
    // this.categoryService.createNew(this.category).subscribe(data => {
    //   console.log(data);
    //   Swal.fire(
    //     'Đã thêm!',
    //     'Dữ liệu đã được thêm thành công.',
    //     'success'
    //   );  
    // })
    // addForm.reset();

  }

}
