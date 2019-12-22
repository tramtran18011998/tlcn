import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductComponent } from './product/product.component';
import { AccessoryComponent } from './accessory/accessory.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';
import { CartComponent } from './cart/cart.component';
import { ProductpageComponent } from './productpage/productpage.component';
import { AdminComponent } from './admin/admin.component';
import { AdminProductComponent } from './admin/admin-product/admin-product.component';
import { AdsidebarComponent } from './admin/adsidebar/adsidebar.component';
import { AdnavbarComponent } from './admin/adnavbar/adnavbar.component';
import { AdProductSeeComponent } from './admin/admin-product/ad-product-see/ad-product-see.component';
import { AdProductEditComponent } from './admin/admin-product/ad-product-edit/ad-product-edit.component';
import { AdProductAddComponent } from './admin/admin-product/ad-product-add/ad-product-add.component';
import { AdminAccessoriesComponent } from './admin/admin-accessories/admin-accessories.component';
import { AdAccessoriesAddComponent } from './admin/admin-accessories/ad-accessories-add/ad-accessories-add.component';
import { AdAccessoriesEditComponent } from './admin/admin-accessories/ad-accessories-edit/ad-accessories-edit.component';
import { AdAccessoriesSeeComponent } from './admin/admin-accessories/ad-accessories-see/ad-accessories-see.component';
import { AdminCustomerComponent } from './admin/admin-customer/admin-customer.component';
import { AdCustomerAddComponent } from './admin/admin-customer/ad-customer-add/ad-customer-add.component';
import { AdCustomerSeeComponent } from './admin/admin-customer/ad-customer-see/ad-customer-see.component';
import { AdCustomerEditComponent } from './admin/admin-customer/ad-customer-edit/ad-customer-edit.component';
import { AdminSupplierComponent } from './admin/admin-supplier/admin-supplier.component';
import { AdSupplierAddComponent } from './admin/admin-supplier/ad-supplier-add/ad-supplier-add.component';
import { AdSupplierSeeComponent } from './admin/admin-supplier/ad-supplier-see/ad-supplier-see.component';
import { AdSupplierEditComponent } from './admin/admin-supplier/ad-supplier-edit/ad-supplier-edit.component';
import { AdminWorkerComponent } from './admin/admin-worker/admin-worker.component';
import { AdWorkerAddComponent } from './admin/admin-worker/ad-worker-add/ad-worker-add.component';
import { AdWorkerEditComponent } from './admin/admin-worker/ad-worker-edit/ad-worker-edit.component';
import { AdWorkerSeeComponent } from './admin/admin-worker/ad-worker-see/ad-worker-see.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule, MatSortModule} from '@angular/material';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSliderModule} from '@angular/material/slider';
import { ProfileComponent } from './profile/profile.component';
import { AdminCategoryComponent } from './admin/admin-category/admin-category.component';
import { AdCategoryAddComponent } from './admin/admin-category/ad-category-add/ad-category-add.component';
import { AdCategoryEditComponent } from './admin/admin-category/ad-category-edit/ad-category-edit.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    ProductComponent,
    AccessoryComponent,
    AboutComponent,
    ContactComponent,
    LoginComponent,
    CartComponent,
    ProductpageComponent,
    AdminComponent,
    AdminProductComponent,
    AdsidebarComponent,
    AdnavbarComponent,
    AdProductSeeComponent,
    AdProductEditComponent,
    AdProductAddComponent,
    AdminAccessoriesComponent,
    AdAccessoriesAddComponent,
    AdAccessoriesEditComponent,
    AdAccessoriesSeeComponent,
    AdminCustomerComponent,
    AdCustomerAddComponent,
    AdCustomerSeeComponent,
    AdCustomerEditComponent,
    AdminSupplierComponent,
    AdSupplierAddComponent,
    AdSupplierSeeComponent,
    AdSupplierEditComponent,
    AdminWorkerComponent,
    AdWorkerAddComponent,
    AdWorkerEditComponent,
    AdWorkerSeeComponent,
    ProfileComponent,
    AdminCategoryComponent,
    AdCategoryAddComponent,
    AdCategoryEditComponent,
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatTableModule,
    MatPaginatorModule,
    MatSelectModule,
    MatSortModule,
    MatFormFieldModule, 
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
