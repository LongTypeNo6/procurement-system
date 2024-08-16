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

function uploadFile(fileInputId, subDirectory, fileId) {
    const files = document.querySelector(`#${fileInputId}`).files;

    Array.from(files).reduce((promiseChain, file, index) => {
        return promiseChain.then(() => {
            return checkFileExists(file.name).then(exists => {
                if (exists) {
                    return confirm(`파일 ${file.name} 이미 존재합니다. 덮어쓰시겠습니까?`);
                }
                return true;
            }).then(overwrite => {
                if (overwrite) {
                    const formData = new FormData();
                    formData.append('files', file);
                    formData.append('overwrite', true);
                    formData.append('subDirectory', subDirectory);
                    formData.append('fileId', fileId + '-' + index); // DB에만 저장되는 ID, 파일 이름에 붙지 않음
                    return proceedUpload(formData);
                }
            });
        });
    }, Promise.resolve()).then(() => {
        console.log("파일 모두 저장");
    }).catch(error => {
        console.error('Error:', error);
    });
}

function checkFileExists(fileName) {
    return fetch(`/api/files/check?fileName=${encodeURIComponent(fileName)}`, {
        method: 'POST',
    })
        .then(response => response.json());
}

function proceedUpload(formData) {
    return fetch('/api/files/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(message => console.log(message))
        .catch(error => console.error('Error:', error));
}


function loadFileList() {
    fetch('/api/files/list')
        .then(response => response.json())
        .then(files => {
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = '';
            files.forEach(file => {
                const li = document.createElement('li');
                li.innerHTML = `<a href="/api/files/download/${file.id}">${file.originalName}</a>`;
                const deleteButton = document.createElement('button');
                deleteButton.textContent = '삭제';
                deleteButton.onclick = () => deleteFile(file.id);
                li.appendChild(deleteButton);
                fileList.appendChild(li);
            });
        });
}

function deleteFile(fileId) {
    fetch(`/api/files/delete/${fileId}`, {
        method: 'DELETE'
    })
        .then(() => {
            alert('파일이 삭제되었습니다.');
            loadFileList();
        })
        .catch(error => {
            alert('파일 삭제 실패');
            console.error('Error:', error);
        });
}
