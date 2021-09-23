var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
let keyword, searchRes, count;
const fetchAPI = async () => {
    keyword = decodeURI(location.pathname.split('/')[2]);
    localStorage.setItem('page', '1');

    const res = await fetch(`/dev/searchResult?keyword=${keyword}`);
    const data = await res.text();
    searchRes = (JSON.parse(data));
    count = searchRes['size'];
    const resultItemCount = document.querySelector('.result-item-count');
    resultItemCount.textContent = count;
    const script = document.createElement('script');
    script.src = '/js/searchResult.js';
    document.body.appendChild(script);
};

fetchAPI()
    .catch(e => console.error(e));