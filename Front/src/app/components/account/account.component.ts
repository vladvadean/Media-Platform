import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {BillingDetails} from "../../models/billingDetails";
import {BillingDetailsService} from "../../services/billingDetails.service";
import {FormsModule} from "@angular/forms";
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {Content} from '../../models/content';
import {ContentService} from '../../services/content.service';
import {LikedContentService} from "../../services/likedContent.service";
import {LikedContent} from "../../models/likedContent";
import {ChangeDetectorRef} from '@angular/core';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  imports: [
    NgClass,
    NgIf,
    DatePipe,
    NgForOf,
    FormsModule
  ],
  standalone: true
})
export class AccountComponent implements OnInit {
  menuVisible: boolean = false;
  username: string | undefined = '';
  id: string | undefined = '';
  billingDetails: BillingDetails[] = [];
  user: User | null = null; // User information
  isPopupVisible: boolean = false; // To control the visibility of the account information popup
  isDeletePopupVisible: boolean = false; // To control the visibility of the delete confirmation popup
  isBillingPopupVisible: boolean = false;
  isPaymentPopupVisible: boolean = false;
  latestBill: BillingDetails | null = null;
  todayDate: Date = new Date(); // Property to hold today's date
  errorMessage: string = '';
  likedContent: Content[] = [];
  toggleShowLikedContent: boolean = false;

  formBillingDetails: Partial<BillingDetails> = {
    cardNo: '',
    name: '',
    transactionDate: this.todayDate,
    cvv: ''
  };

  videos: Content[] = [];
  toggleSortDate: boolean = false;
  toggleSortTitle: boolean = false;
  unsortedVideos: Content[] = [];

  constructor(
    private likedContentService: LikedContentService,
    private userService: UserService,
    private router: Router,
    private billingsService: BillingDetailsService,
    private contentService: ContentService,
    private sanitizer: DomSanitizer,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit() {
    this.userService.getUser().subscribe(user => {
      if (user) {
        this.user = user;
        this.username = user.username;
        this.id = user.id;
        this.userService.addUser(this.user);
      }
    });
    this.showBills();
    this.loadContent();
    if (this.id) {
      this.likedContentService.getContentOfUser(this.id).subscribe(likedVideos => {
        if (likedVideos) {
          this.likedContent = likedVideos;
        }
      });
    }
    console.log("cxc" + this.likedContent.length)
  }

  getInitial(): string {
    return this.user?.username ? this.user.username.charAt(0).toUpperCase() : 'X';
  }

  toggleMenu() {
    this.menuVisible = !this.menuVisible;
    if (!this.menuVisible) {
      this.isBillingPopupVisible = false;
      this.isDeletePopupVisible = false;
      this.isPopupVisible = false;
      this.isPaymentPopupVisible = false;
    }
  }

  togglePopup() {
    this.isPopupVisible = !this.isPopupVisible;
  }

  toggleBillingPopup() {
    if (!this.isPaymentPopupVisible) {
      this.isBillingPopupVisible = !this.isBillingPopupVisible;
    }
  }

  toggleDeletePopup() {
    this.isDeletePopupVisible = !this.isDeletePopupVisible;
  }

  togglePaymentPopup() {
    if (!this.isBillingPopupVisible) {
      this.isPaymentPopupVisible = !this.isPaymentPopupVisible;
      if (this.latestBill) {
        this.formBillingDetails = {...this.latestBill, transactionDate: this.todayDate};
      }
    }
  }

  confirmDeleteUser() {
    if (this.user) {
      this.userService.deleteUser(this.user).subscribe(response => {
        console.log('User deleted', response);
        this.isDeletePopupVisible = false; // Close the delete confirmation popup after deletion
        this.isPopupVisible = false; // Close the account information popup after deletion
        this.router.navigate(['/signInSignUp']); // Navigate to sign-in page
      });
    }
  }

  showBills() {
    if (this.user && this.id) {
      this.billingsService.getBillingDetailsOfUser(this.id).subscribe({
        next: data => {
          this.billingDetails = data;
          this.latestBill = this.billingsService.getMostRecentBill(this.billingDetails);
          console.log('Billing details for user', this.latestBill.transactionDate);
        },
        error: err => {
          console.error('No billing details stored', err);
          this.billingDetails = [];
        }
      });
    }
  }

  addBillingDetails() {
    if (this.formBillingDetails.cardNo && this.formBillingDetails.name && this.formBillingDetails.cvv) {
      const newBillingDetails: BillingDetails = {
        id: '', // Will be set by the server
        userId: this.id || '',
        cardNo: this.formBillingDetails.cardNo,
        name: this.formBillingDetails.name,
        transactionDate: this.todayDate, // Set to today's date
        cvv: this.formBillingDetails.cvv
      };

      if (this.user) {
        this.user.lastPaymentDate = this.todayDate;
        this.userService.updateUser(this.user).subscribe({
          next: data => {
            this.userService.setUser(data);
          }
        });
      }

      this.billingsService.addBillingDetails(newBillingDetails).subscribe(response => {
        this.latestBill = response; // Set the new bill as the latest bill
        this.billingDetails.push(response); // Add the new bill to the list of billing details
        console.log('New billing details added!', response);
        this.togglePaymentPopup();
      });
    } else {
      console.log('All fields are required');
    }
  }

  canAddBillingDetails(): number {
    if (this.latestBill) {
      const today = new Date();
      const lastTransactionDate = new Date(this.latestBill.transactionDate);
      console.log("!!X!!" + lastTransactionDate);
      const diffTime = Math.abs(today.getTime() - lastTransactionDate.getTime());
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      return diffDays >= 30 ? 0 : 30 - diffDays;
    }
    return 30;
  }

  addBillingIfAllowed() {
    console.log("!!")
    const daysRemaining = this.canAddBillingDetails();
    if (daysRemaining === 0) {
      this.addBillingDetails();
    } else {
      this.errorMessage = `You can only pay after ${daysRemaining} days.`;
      setTimeout(() => {
        this.errorMessage = '';
      }, 3000);
    }
  }

  loadContent(): void {
    this.contentService.getAllContent().subscribe({
      next: (content) => {
        this.videos = content;
        this.unsortedVideos = content;
      },
      error: (err) => {
        console.error('Error fetching content', err);
      },
    });
  }

  getVideoUrl(videoId: string): SafeResourceUrl {
    const url = `https://www.youtube.com/embed/${videoId}`;
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  sortByMostRecent(): void {
    if (!this.toggleSortDate) {
      this.videos.sort((a, b) => new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime());
      this.toggleSortTitle = false;
    } else {
      this.videos = this.unsortedVideos;
      this.loadContent();
    }
    this.toggleSortDate = !this.toggleSortDate;
  }

  sortByTitle(): void {
    if (!this.toggleSortTitle) {
      this.videos.sort((a, b) => a.title.localeCompare(b.title));
      this.toggleSortDate = false;
    } else {
      this.videos = this.unsortedVideos;
      this.loadContent();
    }
    this.toggleSortTitle = !this.toggleSortTitle;
    console.log(this.toggleSortTitle);
  }

  toggleLikedContent(video: Content): void {
    if (this.id == null) {
      return;
    }

    // Check if the video is already liked
    const likedVideo = this.likedContent.find(content => content.id === video.id);

    if (likedVideo) {
      // If the video is already liked, remove it from the liked content
      this.likedContentService.getLikedContentOfContentAndUser(this.id, video.id).subscribe({
        next: (likedContent) => {
          this.likedContentService.deleteLikedContent(likedContent.id).subscribe({
            next: () => {
              // Remove the video from the likedContent array
              this.likedContent = this.likedContent.filter(content => content.id !== video.id);
              console.log('Video removed from liked content');
              // If showing liked content, update the videos list
              if (this.toggleShowLikedContent) {
                this.videos = this.likedContent;
              }
              this.cdr.detectChanges();
            },
            error: (err) => {
              console.log('Error removing liked content', err);
            }
          });
        },
        error: (err) => {
          console.log('Error fetching liked content', err);
        }
      });
    } else {
      // If the video is not liked, add it to the liked content
      const likedContent: LikedContent = {
        id: '',
        contentId: video.id,
        userId: this.id
      };

      this.likedContentService.addLikedContent(likedContent).subscribe({
        next: (newLikedContent) => {
          // Add the video to the likedContent array
          this.likedContent.push(video);
          console.log('Video added to liked content', newLikedContent);
          // If showing liked content, update the videos list
          if (this.toggleShowLikedContent) {
            this.videos = this.likedContent;
          }
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.log('Error adding liked content', err);
        }
      });
    }
  }

  showLikedContent(): void {
    if (!this.toggleShowLikedContent) {
      this.videos = this.likedContent;
    } else {
      this.videos = this.unsortedVideos;
    }
    this.toggleShowLikedContent = !this.toggleShowLikedContent;
  }

  isLiked(content: Content): boolean {
    return this.likedContent.some(liked => liked.id === content.id);
  }
}
