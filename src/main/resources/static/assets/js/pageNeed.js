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
    var date = new Date(dateString);
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2);
    var day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
}

// 시간을 "HH:mm" 형식의 문자열로 변환
function formatTime(dateString) {
    var date = new Date(dateString); // 'Date'로 수정
    var hours = ('0' + date.getHours()).slice(-2); // 시간을 2자리로 보장
    var minutes = ('0' + date.getMinutes()).slice(-2); // 분을 2자리로 보장
    return hours + ':' + minutes;
}

// "yyyy-MM-dd" 형식의 문자열을 Date 객체로 변환
function parseDate(dateString) {
    var parts = dateString.split('-');
    var year = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10) - 1; // 월은 0부터 시작
    var day = parseInt(parts[2], 10);

    return new Date(year, month, day);
}

// 무한 스크롤 기능
function setupInfiniteScroll(loadMoreDataCallback) {
    let isLoading = false;

    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() >= $(document).height() - 10) {
            if (!isLoading && currentPage <= totalPage) { // 수정된 코드: currentPage가 totalPage 이하인 경우에만 요청
                isLoading = true;
                currentPage++;
                loadMoreDataCallback(currentPage).always(function() {
                    isLoading = false;
                });
            }
        }
    });
}
