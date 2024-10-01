import { CommonModule, isPlatformBrowser } from '@angular/common';
import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  PLATFORM_ID,
  ViewChild,
} from '@angular/core';
import { DataTableDirective, DataTablesModule } from 'angular-datatables';
import { Post } from '../../model/post';
import { PostService } from '../../service/post.service';
import { Config } from 'datatables.net';
import { Subject } from 'rxjs';
import { jqxGridModule } from 'jqwidgets-ng/jqxgrid';

@Component({
  selector: 'app-manage-posts',
  standalone: true,
  imports: [DataTablesModule, CommonModule, jqxGridModule],
  templateUrl: './manage-posts.component.html',
  styleUrl: './manage-posts.component.css',
})
export class ManagePostsComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild(DataTableDirective, { static: false })
  private dtElement!: DataTableDirective;
  posts: Post[] = [];
  source: Post[] = [];
  infoData: any = [];

  dtOptions: Config = {};
  dtTrigger: Subject<any> = new Subject();

  constructor(private postService: PostService) {}

  columns = [
    { text: 'Id', datafield: 'id' },
    { text: 'Title', datafield: 'title' },
  ];

  ngOnInit(): void {
    this.postService.findAllPosts().subscribe((result) => {
      this.source = result;
      this.posts = result;
    });

    this.infoData = [
      { id: '1', title: 'Post 1', categoryId: 'Category 1' },
      { id: '2', title: 'Post 2', categoryId: 'Category 2' },
    ];
    // this.postService.findAllPosts().subscribe((result) => {
    //   this.posts = result;
    // });

    // this.dtOptions = {
    //   serverSide: true, // Set the flag
    //   processing: true,
    //   ajax: (dataTablesParameters: any, callback) => {
    //     this.postService.findAllPosts().subscribe((data) => {
    //       this.posts = data;
    //       callback({
    //         recordsTotal: data.length,
    //         recordsFiltered: data.length,
    //         data: data,
    //       });
    //     });
    //   },
    //   columns: [
    //     {
    //       title: 'ID',
    //       data: 'id',
    //     },
    //     {
    //       title: 'Category',
    //       data: 'categoryId',
    //     },
    //     {
    //       title: 'Title',
    //       data: 'title',
    //     },
    //   ],
    // };
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next(null);
    // this.dtElement.dtInstance.then((dtInstance) => {
    //   dtInstance.columns().every(function () {
    //     const that = this;
    //     $('input', this.footer()).on('keyup change', function () {
    //       if (that.search() !== (this as HTMLInputElement).value) {
    //         that.search((this as HTMLInputElement).value).draw();
    //       }
    //     });
    //   });
    // });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance) => {
      // Destroy the table first
      dtInstance.destroy();
      // Call the dtTrigger to rerender again
      this.dtTrigger.next(null);
    });
  }
}
