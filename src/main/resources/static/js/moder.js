const BASE_URL = 'https://govorigraya.ru/api'
const DOWNLOAD_DAY_APPOINTMENTS = BASE_URL + '/appointment'

const initDownloadDayAppointmentsForm = () => {
    const form = document.getElementById("download-form")
    const button = form.querySelector('#action')
    const dateField = form.querySelector('input[type="date"]')
    const today = new Date().toISOString().slice(0,10)
    dateField.value = today
    dateField.maxValue = today

    form?.addEventListener("submit", e => {
        e.preventDefault()

        const params = new URLSearchParams();
        params.append('date', dateField.value);

        fetch(DOWNLOAD_DAY_APPOINTMENTS + "?" + params.toString())
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.blob();
            })
            .then(blob => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;

                // Customize the filename here
                const filename = `appointments_${dateField.value}.xlsx`;

                a.setAttribute('download', filename);
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            })
            .catch(error => console.error('Error downloading file:', error));
    })
}
initDownloadDayAppointmentsForm()