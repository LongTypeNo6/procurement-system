// HTML 이스케이프 처리 함수
function escapeHtml(text) {
    return text
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}

// 날짜를 "yyyy-MM-dd" 형식의 문자열로 변환
function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
}

// 시간을 "HH:mm" 형식의 문자열로 변환
function formatTime(dateString) {
    const date = new Date(dateString); // 'Date'로 수정
    const hours = ('0' + date.getHours()).slice(-2); // 시간을 2자리로 보장
    const minutes = ('0' + date.getMinutes()).slice(-2); // 분을 2자리로 보장
    return hours + ':' + minutes;
}

// "yyyy-MM-dd" 형식의 문자열을 Date 객체로 변환
function parseDate(dateString) {
    const parts = dateString.split('-');
    const year = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1; // 월은 0부터 시작
    const day = parseInt(parts[2], 10);

    return new Date(year, month, day);
}

// 무한 스크롤 기능
function setupInfiniteScroll(loadMoreDataCallback) {
    let isLoading = false;

    $(window).scroll(function () {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 10) {
            if (!isLoading && currentPage <= totalPage) { // 수정된 코드: currentPage가 totalPage 이하인 경우에만 요청
                isLoading = true;
                currentPage++;
                loadMoreDataCallback(currentPage).always(function () {
                    isLoading = false;
                });
            }
        }
    });
}
//오늘 날짜 yyyy-MM-dd형태 불러오기
function todayValue() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    const todayValue = `${year}-${month}-${day}`;
    return todayValue;
}

