const BASE_URL = 'http://localhost:8080'
const REVIEW_ENDPOINT = BASE_URL + '/review'
const APPOINTMENT_ENDPOINT = BASE_URL + '/appointment'

/** Форма принимает
    review: string
    name: string
*/
const initReviewForm = () => {
    const form = document.getElementById('review-form')
    const button = document.getElementById('review-form-button')
    const success = document.getElementById('submition-succcess')
    const err = document.getElementById('submition-error')

    form?.addEventListener("submit", e => {
        e.preventDefault()

        button.disabled = true
        button.style.animation = 'pulse infinite 1.5s linear'
        success.style.display = 'none'
        err.style.display = 'none'

        fetch(REVIEW_ENDPOINT, {
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

/** Форма принимает
    name: string
    phone: string
    email: string
*/
const initAppointmentForm = () => {
    const form = document.getElementById('appointment-form')
    const button = document.getElementById('action')
    const success = document.getElementById('submition-succcess')
    const err = document.getElementById('submition-error')

    form?.addEventListener("submit", e => {
        button.disabled = true
        success.style.display = 'none'
        button.style.animation = 'pulse infinite 1.5s linear'
        err.style.display = 'none'

        fetch(APPOINTMENT_ENDPOINT, {
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
        e.preventDefault()
    })
}


initReviewForm()
initAppointmentForm()

const hamburger = document.querySelector('.hamburger')
const hamburgerLinks = document.getElementById('hamburger-links')
hamburger.addEventListener('click', e => {
    e.target.classList.toggle('clicked')
    hamburgerLinks.style.display = hamburgerLinks.style.display === 'none' ? 'flex' : 'none'
})
hamburgerLinks.addEventListener('click', e => {
    hamburger.classList.remove('clicked')
    hamburgerLinks.style.display = 'none'
})

const floatingHeader = document.getElementById('floating-header')
floatingHeader && document.addEventListener('scroll', e => {
    if (window.scrollY > 500) {        
        floatingHeader.style.position = 'sticky'
        floatingHeader.style.display = 'flex'
        floatingHeader.style.transform = 'translateY(0%)'
        floatingHeader.style.opacity = '1'
    } else {        
        floatingHeader.style.position = 'absolute'
        floatingHeader.style.display = 'none'
        floatingHeader.style.transform = 'translateY(-20%)'
        floatingHeader.style.opacity = '0'
    }
})

const questions = document.getElementById('questions')
questions?.addEventListener('click', e => {
    if (e.target.className === 'content-card') {
        const content = e.target.getElementsByClassName('content')[0]
        const plus = e.target.getElementsByClassName('plus')[0]
        if (content.style.display === 'none' || content.style.display === '') {
            content.style.display = 'flex'
            plus.style.transform = 'rotate(45deg)'
        } else if (content?.style.display === 'flex') {
            content.style.display = 'none'
            plus.style.transform = 'rotate(0deg)'
        }
    }    
})
