const createBtnContainer = (id, isTestable) => {
  const putInCartBtn = document.createElement('button');
  putInCartBtn.classList += 'cont-btn put-in-cart';
  putInCartBtn.addEventListener('click', () => location.href = `/cartList/${localStorage.getItem('store_id')}`);

  const goTestBtn = document.createElement('button');
  if (isTestable === 'Y') {
    goTestBtn.classList += 'cont-btn go-test';
    goTestBtn.addEventListener('click', () => location.href = `/test/${id}`);
  } else {
    goTestBtn.style.display = 'none';
  }

  const viewReviewBtn = document.createElement('button');
  viewReviewBtn.classList += 'cont-btn view-review';
  viewReviewBtn.addEventListener('click', () => location.href = `/review/${id}`);
  return [putInCartBtn, goTestBtn, viewReviewBtn]
}