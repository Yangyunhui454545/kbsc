interface Review {
  reviewId: string,
  rate: string | number,
  image: string,
  content: string,
  date: string
}

const goPrevBtn: HTMLButtonElement = document.querySelector('.go-prev') as HTMLButtonElement;
goPrevBtn.addEventListener('click', () => history.back());
const btnContainer: HTMLDivElement = document.querySelector('.btn-container') as HTMLDivElement;
createBtnContainer(itemId, isTestable).forEach(el =>
  btnContainer.appendChild(el));
const reviewContainer: HTMLDivElement = document.querySelector('.review-container') as HTMLDivElement;


const rateList: number[] = [];
const reviewList: Review[] = [];

if (rateCnt) {
  reviewData.forEach(function (review: Review) {
    rateList.push(Number(review.rate));
    reviewList.push({
      reviewId: review.reviewId,
      rate: review.rate,
      content: review.content,
      image: review.image,
      date: review.date.split('T')[0]
    });
  });

  const avgRate: number = (rateList.reduce((a, x) => a += x)) / rateCnt;

  const starMaker = (rate: number) => {
    if (rate < 2) return '⭐';
    else if (rate < 3) return '⭐⭐';
    else if (rate < 4) return '⭐⭐⭐';
    else if (rate < 5) return '⭐⭐⭐⭐';
    else return '⭐⭐⭐⭐⭐';
  }

  const rateRowStarRate: HTMLDivElement = document.querySelector('.rate-row > .star-rate') as HTMLDivElement;
  rateRowStarRate.textContent = starMaker(avgRate);
  const rateRowNumRate: HTMLDivElement = document.querySelector('.rate-row > .num-rate') as HTMLDivElement;
  rateRowNumRate.innerHTML = `${avgRate.toFixed(1)} <span class="light-color"> / 5<sub class="little-size">점</sub></span>`;


  const createRateRow = (review: Review): HTMLDivElement => {
    const reviewRow = document.createElement('div');
    reviewRow.classList.add('review-row');

    const reviewInfo = document.createElement('div');
    reviewInfo.classList.add('review-info');

    const starRate = document.createElement('div');
    starRate.classList.add('star-rate');
    /* 나중에 별 넣기 */
    starRate.textContent = starMaker(Number(review.rate));
    const numRate = document.createElement('div');
    numRate.classList.add('num-rate');
    numRate.textContent = `${review.rate}점`;

    reviewInfo.appendChild(starRate);
    reviewInfo.appendChild(numRate);

    const reviewContent = document.createElement('div');
    reviewContent.classList.add('review-content');

    const reviewText = document.createElement('p');
    reviewText.classList.add('review-text');
    reviewText.textContent = review.content;

    reviewContent.appendChild(reviewText);

    if (review.image) {
      const reviewImgContainer = document.createElement('div');
      reviewImgContainer.classList.add('review-img-container');

      const reviewImg = document.createElement('img');
      reviewImg.classList.add('review-img');
      reviewImg.src = review.image;

      reviewImgContainer.appendChild(reviewImg);
      reviewContent.appendChild(reviewImgContainer);
    }

    const reviewDate = document.createElement('p');
    reviewDate.classList.add('review-date');

    const dateText = review.date.split('T')[0].replace(/-/gi, '. ');
    reviewDate.textContent = dateText;

    reviewContent.appendChild(reviewDate);

    reviewRow.appendChild(reviewInfo);
    reviewRow.appendChild(reviewContent);

    return reviewRow;
  };

  const reviewElList: HTMLDivElement[] = [];
  reviewList.forEach(function (review) {
    reviewElList.push(createRateRow(review));
  })

  reviewElList.forEach(review => {
    reviewContainer.appendChild(review);
  });

} else {
  reviewContainer.classList.add('no-review');
  reviewContainer.textContent = '리뷰가 없는 상품입니다.'
}