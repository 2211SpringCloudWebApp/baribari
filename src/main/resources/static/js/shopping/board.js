// 네비게이션바 아이템들과 페이지 요소들을 가져옵니다.
const navItems = document.querySelectorAll('#productNav a');
const pages = document.querySelectorAll('#productInfo, #productReview, #productQnA, #productAS');

// 네비게이션바 아이템들에 클릭 이벤트를 추가합니다.
navItems.forEach((item, index) => {
    item.addEventListener('click', (event) => {
        event.preventDefault(); // 링크 이동을 막습니다.

        // 클릭된 아이템을 활성화하고, 다른 아이템은 비활성화합니다.
        navItems.forEach((navItem) => {
            navItem.classList.remove('active');
        });
        item.classList.add('active');

        // 클릭된 아이템과 같은 인덱스의 페이지를 보여주고, 다른 페이지는 숨깁니다.
        pages.forEach((page) => {
            page.style.display = 'none';
        });
        pages[index].style.display = 'block';
    });
});

// 상품후기 게시판 아이템들을 가져옵니다.
const reviewItems = document.querySelectorAll('#reviewBoard .reviewItem');

// 페이지네이션 아이템들을 가져옵니다.
const paginationItems = document.querySelectorAll('#reviewPagination .paginationItem');

// 페이지네이션 아이템들에 클릭 이벤트를 추가합니다.
paginationItems.forEach((item) => {
    item.addEventListener('click', (event) => {
        event.preventDefault(); // 링크 이동을 막습니다.

        // 클릭된 아이템을 활성화하고, 다른 아이템은 비활성화합니다.
        paginationItems.forEach((paginationItem) => {
            paginationItem.classList.remove('active');
        });
        item.classList.add('active');

        // 클릭된 아이템의 인덱스에 해당하는 게시판 아이템들을 보여주고, 다른 게시판 아이템들은 숨깁니다.
        const index = Array.from(paginationItems).indexOf(item);
        const startIndex = index * 10;
        const endIndex = startIndex + 9;
        reviewItems.forEach((reviewItem, reviewIndex) => {
            if (reviewIndex >= startIndex && reviewIndex <= endIndex) {
                reviewItem.style.display = 'block';
            } else {
                reviewItem.style.display = 'none';
            }
        });
    });
});

// 페이징 관련 변수 선언
const reviewPagination = document.querySelector('#reviewPagination');
const prevBtn = reviewPagination.querySelector('.prev');
const nextBtn = reviewPagination.querySelector('.next');
const pageItems = reviewPagination.querySelectorAll('li:not(.prev):not(.next)');
const pageCount = pageItems.length;

// 현재 페이지 번호를 저장하는 변수 초기화
let currentPage = 1;

// 다음 페이지로 이동하는 함수
function goToNextPage() {
    if (currentPage < pageCount) {
        currentPage++;
        updatePagination();
    }
}

// 이전 페이지로 이동하는 함수
function goToPrevPage() {
    if (currentPage > 1) {
        currentPage--;
        updatePagination();
    }
}

// 페이지 번호를 클릭했을 때 해당 페이지로 이동하는 함수
function goToPage(pageNumber) {
    currentPage = pageNumber;
    updatePagination();
}

// 페이징 UI를 업데이트하는 함수
function updatePagination() {
    // 현재 페이지와 일치하는 페이지 번호를 가진 li 요소에 active 클래스 추가
    pageItems.forEach((pageItem, index) => {
        if (index + 1 === currentPage) {
            pageItem.classList.add('active');
        } else {
            pageItem.classList.remove('active');
        }
    });

    // 이전 버튼 활성화 여부 결정
    if (currentPage === 1) {
        prevBtn.classList.add('disabled');
    } else {
        prevBtn.classList.remove('disabled');
    }

    // 다음 버튼 활성화 여부 결정
    if (currentPage === pageCount) {
        nextBtn.classList.add('disabled');
    } else {
        nextBtn.classList.remove('disabled');
    }
}

// prev 버튼에 클릭 이벤트 추가
prevBtn.addEventListener('click', goToPrevPage);

// next 버튼에 클릭 이벤트 추가
nextBtn.addEventListener('click', goToNextPage);

// 각 페이지 번호에 클릭 이벤트 추가
pageItems.forEach((pageItem, index) => {
    pageItem.addEventListener('click', () => {
        goToPage(index + 1);
    });
});

//////////////////////////////////////////////////////////////
// 상품 후기 
// 상품Q&A 게시판 아이템들을 가져옵니다.

const qnaBoard = document.getElementById("productQnA");

// 상품 문의 탭 숨김
qnaBoard.style.display = "none";

// 토글 함수
function toggleQnA() {
    if (qnaBoard.style.display === "none") {
        qnaBoard.style.display = "block";
    } else {
        qnaBoard.style.display = "none";
    }
}



