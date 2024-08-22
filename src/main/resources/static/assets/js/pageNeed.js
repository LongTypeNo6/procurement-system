// HTML 이스케이프 처리 함수
function escapeHtml(text) {
    return text
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}
//받은날짜를 다음 평일로 리턴
function nextWeekday(inputDateValue) {

    const inputDate = new Date(inputDateValue);
    let nextWeekday = new Date(inputDate);

    // 다음 평일 계산
    do {
        nextWeekday.setDate(nextWeekday.getDate() + 1);
    } while (nextWeekday.getDay() === 0 || nextWeekday.getDay() === 6); // 0 = Sunday, 6 = Saturday

    return formatDate(nextWeekday)
}
//받은날짜를 이전 평일로 리턴
function prevWeekday(inputDateValue) {

    const inputDate = new Date(inputDateValue);
    let prevWeekday = new Date(inputDate);

    // 이전 평일 계산
    do {
        prevWeekday.setDate(prevWeekday.getDate() - 1);
    } while (prevWeekday.getDay() === 0 || prevWeekday.getDay() === 6); // 0 = Sunday, 6 = Saturday

    return formatDate(prevWeekday)
}

// 날짜를 "MM/dd" 형식의 문자열로 변환
function formatDateS(dateString) {
    const date = new Date(dateString);
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return month + '/' + day;
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
    return dateString+"T00:00:00"
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
//두번째 단어까지+...리턴, 주소지에 사용예정
function getFirstTwoWords(input) {
    // 공백을 기준으로 문자열을 나눕니다.
    var words = input.split(' ');
    // 배열의 첫 번째와 두 번째 요소를 합쳐서 반환합니다.
    if (words.length >= 2) {
        return words[0] + ' ' + words[1]+' ···';
    }
    // 단어가 2개 미만일 경우 그대로 반환합니다.
    return input;
}
//값이 널인지 아닌지
function checkNull(inputElement) {
    var value = inputElement.value.trim(); // 입력 값에서 앞뒤 공백을 제거합니다.
    var result = value === "" ? null : value;
    return result;
}
