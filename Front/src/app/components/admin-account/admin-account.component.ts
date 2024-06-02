import {Component, OnInit} from '@angular/core';
import {DatePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {Admin} from '../../models/admin';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {AdminService} from '../../services/admin.service';
import {User} from '../../models/user';
import {FormsModule} from '@angular/forms';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {Content} from '../../models/content';
import {ContentService} from "../../services/content.service";

@Component({
  selector: 'app-admin-account',
  standalone: true,
  imports: [NgIf, NgClass, NgForOf, FormsModule, DatePipe],
  templateUrl: './admin-account.component.html',
  styleUrls: ['./admin-account.component.css'],
})
export class AdminAccountComponent implements OnInit {
  menuVisible: boolean = false;
  usersPopup: boolean = false;
  adminsPopup: boolean = false;
  accountPopup: boolean = false;
  deletePopup: boolean = false;
  deleteUserPopup: boolean = false;
  deleteAdminPopup: boolean = false;
  addAdminPopup: boolean = false;
  addContentPopup: boolean = false;
  myContentPopup: boolean = false;
  deleteContentPopup: boolean = false;
  admin: Admin | null = null;
  username: string = '';
  id: string = '';
  users: User[] = [];
  admins: Admin[] = [];
  videos: Content[] = [];
  myContent: Content[] = []; // New state for admin's content
  userToBeDeleted: User | null = null;
  adminToBeDeleted: Admin | null = null;
  contentToBeDeleted: Content | null = null; // New state for content to be deleted
  newAdmin: Partial<Admin> = {};
  newContent: Partial<Content> = {}; // New content state
  errorMessage: string = '';
  contentErrorMessage: string = '';
  toggleSortDate: boolean = false;
  toggleSortTitle: boolean = false;
  unsortedVideos: Content[] = [];

  constructor(
    private adminService: AdminService,
    private userService: UserService,
    private contentService: ContentService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {
  }

  ngOnInit(): void {
    this.adminService.getAdmin().subscribe((admin) => {
      if (admin) {
        this.admin = admin;
        this.username = admin.username;
        this.id = admin.id;
        this.adminService.addAdmin(this.admin);
      }
    });

    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
    });

    this.adminService.getAllAdmins().subscribe({
      next: (admins) => {
        this.admins = admins;
      },
    });

    this.contentService.getAllContent().subscribe({
      next: (content) => {
        this.videos = content;
        this.unsortedVideos = content;
      }
    })
  }

  getVideoUrl(videoId: string): SafeResourceUrl {
    const url = `https://www.youtube.com/embed/${videoId}`;
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  toggleMenu() {
    this.menuVisible = !this.menuVisible;
  }

  toggleUsersPopup() {
    if (!this.accountPopup) {
      this.usersPopup = !this.usersPopup;
    }
  }

  toggleAccountPopup() {
    if (!this.usersPopup) {
      this.accountPopup = !this.accountPopup;
    }
  }

  toggleDeleteUserPopup(user: User | null) {
    this.userToBeDeleted = user;
    if (this.usersPopup) {
      this.deleteUserPopup = !this.deleteUserPopup;
    }
  }

  toggleDeleteAdminPopup(admin: Admin | null) {
    this.adminToBeDeleted = admin;
    if (this.adminsPopup) {
      this.deleteAdminPopup = !this.deleteAdminPopup;
    }
  }

  toggleDeletePopup() {
    this.deletePopup = !this.deletePopup;
  }

  toggleAdminsPopup() {
    this.adminsPopup = !this.adminsPopup;
  }

  toggleAddAdminPopup() {
    this.addAdminPopup = !this.addAdminPopup;
  }

  toggleAddContentPopup() {
    this.addContentPopup = !this.addContentPopup;
  }

  toggleMyContentPopup() { // New method to toggle My Content popup
    this.myContentPopup = !this.myContentPopup;
    if (this.myContentPopup) {
      this.loadMyContent();
    }
  }

  toggleDeleteContentPopup(content: Content | null) { // New method to toggle Delete Content popup
    this.contentToBeDeleted = content;
    if (this.myContentPopup) {
      this.deleteContentPopup = !this.deleteContentPopup;
    }
  }

  confirmDeleteAdmin() {
    if (this.admin) {
      this.adminService.deleteAdmin(this.admin).subscribe((response) => {
        console.log('Admin deleted', response);
        this.deletePopup = false; // Close the delete confirmation popup after deletion
        this.accountPopup = false; // Close the account information popup after deletion
        this.router.navigate(['/signInSignUp']); // Navigate to sign-in page
      });
    }
  }

  confirmDeleteUser(): void {
    if (this.userToBeDeleted) {
      this.userService.deleteUser(this.userToBeDeleted).subscribe({
        next: (data) => {
          console.log('User deleted', data);
          this.deleteUserPopup = false; // Close the delete confirmation popup after deletion
          this.refreshUsersList(); // Refresh the user list
        },
        error: (err) => {
          console.error('Error deleting user', err);
        },
      });
    }
  }

  confirmDeleteAdminList(): void {
    if (this.adminToBeDeleted) {
      this.adminService.deleteAdmin(this.adminToBeDeleted).subscribe({
        next: (data) => {
          console.log('Admin deleted', data);
          this.deleteAdminPopup = false; // Close the delete confirmation popup after deletion
          this.refreshAdminList(); // Refresh the admin list
        },
        error: (err) => {
          console.error('Error deleting admin', err);
        },
      });
    }
  }

  confirmDeleteContent(): void { // New method to confirm content deletion
    if (this.contentToBeDeleted) {
      this.contentService.deleteContent(this.contentToBeDeleted.id).subscribe({
        next: (data) => {
          console.log('Content deleted', data);
          this.deleteContentPopup = false; // Close the delete confirmation popup after deletion
          this.refreshContentList(); // Refresh the content list
          this.loadMyContent(); // Refresh the admin's content list
        },
        error: (err) => {
          console.error('Error deleting content', err);
        },
      });
    }
  }

  refreshUsersList(): void {
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (err) => {
        console.error('Error fetching users', err);
      },
    });
  }

  refreshAdminList(): void {
    this.adminService.getAllAdmins().subscribe({
      next: (admins) => {
        this.admins = admins;
      },
      error: (err) => {
        console.error('Error fetching admins', err);
      },
    });
  }

  refreshContentList(): void { // New method to refresh content list
    this.contentService.getAllContent().subscribe({
      next: (content) => {
        this.videos = content;
      },
      error: (err) => {
        console.error('Error fetching content', err);
      },
    });
  }

  loadMyContent(): void {
    if (this.admin) {
      this.contentService.getContentByAdmin(this.admin.id).subscribe({
        next: (content) => {
          this.myContent = content;
        },
        error: (err) => {
          console.error('Error fetching admin content', err);
        },
      });
    }
  }

  addAdmin(): void {
    // Validate that all fields are filled
    if (this.newAdmin.name && this.newAdmin.username && this.newAdmin.password) {
      // Check if an admin with the provided username already exists
      this.adminService.getAdminByUsername(this.newAdmin.username).subscribe({
        next: () => {
          // If the admin exists, set an error message
          console.log('Admin with this username already exists');
          this.errorMessage = 'Admin with this username already exists';
        },
        error: (err) => {
          // If the admin does not exist (assuming 404 means not found), proceed to add the new admin
          const newAdmin: Admin = {
            id: '', // Generate an appropriate ID or handle it in the backend
            name: this.newAdmin.name!,
            username: this.newAdmin.username!,
            password: this.newAdmin.password!,
          };

          // Add the new admin
          this.adminService.addAdmin(newAdmin).subscribe({
            next: (admin) => {
              console.log('Admin added', admin);
              this.addAdminPopup = false; // Close the add admin popup after addition
              this.refreshAdminList(); // Refresh the admin list
              this.newAdmin = {}; // Clear the new admin form
              this.errorMessage = ''; // Clear any error messages
            },
            error: (err) => {
              console.error('Error adding admin', err);
              this.errorMessage = 'Error adding admin';
            },
          });
        },
      });
    } else {
      // If any field is missing, set an error message
      console.error('All fields are required to add a new admin');
      this.errorMessage = 'All fields are required to add a new admin';
    }
  }

  addContent(): void { // New method to add content
    // Validate that all fields are filled
    if (this.newContent.title && this.newContent.link && this.newContent.rating) {
      const newContent: Content = {
        id: '',
        title: this.newContent.title!,
        link: this.newContent.link!,
        rating: this.newContent.rating!,
        duration: "00:10:00",
        releaseDate: new Date(),
        adminId: this.admin?.id ? this.admin.id : ''
      };

      // Add the new content
      this.contentService.addContent(newContent).subscribe({
        next: (content) => {
          console.log('Content added', content);
          this.addContentPopup = false; // Close the add content popup after addition
          this.refreshContentList(); // Refresh the content list
          this.newContent = {}; // Clear the new content form
          this.contentErrorMessage = ''; // Clear any error messages
        },
        error: (err) => {
          console.error('Error adding content', err);
          this.contentErrorMessage = 'Error adding content';
        },
      });
    } else {
      // If any field is missing, set an error message
      console.error('All fields are required to add new content');
      this.contentErrorMessage = 'All fields are required to add new content';
    }
  }

  getInitial(): string {
    return this.admin?.username ? this.admin.username.charAt(0).toUpperCase() : 'X';
  }

  sortByMostRecent(): void {
    if (!this.toggleSortDate) {
      this.videos.sort((a, b) => new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime());
      this.toggleSortTitle = false;
    } else {
      this.videos = this.unsortedVideos;
      this.refreshContentList()
    }
    this.toggleSortDate = !this.toggleSortDate;
  }

  sortByTitle(): void{
    if (!this.toggleSortTitle) {
      this.videos.sort((a, b) => a.title.localeCompare(b.title));
      this.toggleSortDate = false;
    } else {
      this.videos = this.unsortedVideos
      this.refreshContentList()
    }
    this.toggleSortTitle = !this.toggleSortTitle;
    console.log(this.toggleSortTitle)
  }
}
