const BASE_URL = 'https://govorigraya.ru/api'
const ADD_MODER_ENDPOINT = BASE_URL + '/actor/moder'

const initAddModerForm = () => {
    const form = document.getElementById("add-moder-form")
    const button = form.querySelector('#action')
    const success = form.querySelector('#submition-succcess')
    const err = form.querySelector('#submition-error')

    form?.addEventListener("submit", e => {
        e.preventDefault()

        button.disabled = true
        button.style.animation = 'pulse infinite 1.5s linear'
        success.style.display = 'none'
        err.style.display = 'none'

        fetch(ADD_MODER_ENDPOINT, {
            method: 'POST',
            body: new FormData(e.target)
        })
            .then(res => {
                if (res.ok) {
                    form.reset()
                    success.style.display = 'block'
                } else {
                    throw new Error()
                }
            })
            .catch(res => {
                err.style.display = 'block'
            })
            .finally(() => {
                button.disabled = false
                button.style.animation = 'none'
            })
    })
}
initAddModerForm()

