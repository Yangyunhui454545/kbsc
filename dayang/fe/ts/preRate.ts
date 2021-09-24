interface Review {
  reviewId: string,
  content: string,
  rate: string | number,
  image: string,
  date: string
}

interface itemInfo {
  isVegan: string,
  itemDiscountPrice: string,
  itemId: string,
  itemImage: string,
  itemName: string,
  itemPrice: string,
  itemTestable: string
};

let reviewData: Review[];
let itemId: string = decodeURI(location.pathname.split('/')[2]);
let rateCnt: number;
let itemName: string;
let isTestable: string;
const fetchAPI = async () => {
  let res = await fetch(`/dev/item/${itemId}/review`);
  let data: string = await res.text();
  reviewData = (JSON.parse(data))['Review'];
  rateCnt = reviewData.length;
  console.log(reviewData);

  res = await fetch(`/dev/item/${itemId}`);
  data = await res.text();
  itemName = (JSON.parse(data))['Item'].itemName;
  isTestable = (JSON.parse(data))['Item'].itemTestable;

  const titleH1: HTMLHeadingElement = document.querySelector('.title') as HTMLHeadingElement;
  titleH1.textContent = itemName;
  const rateCountP: HTMLParagraphElement = document.querySelector('.rate-count') as HTMLParagraphElement;
  rateCountP.textContent = `총 ${rateCnt} 건`

  const script: HTMLScriptElement = document.createElement('script');
  script.src = '/js/review.js';
  document.body.appendChild(script);
};

fetchAPI()
    .catch(e => console.error(e));