<template>
  <section 
    v-if="account.status" id="loginContainerHome">
    <h3 id="h3Home"> You are logged in! Go to the platform </h3>
    <div id="homeButtonContainer">
      <button v-show="loggedIn" @click="goDashboard"> Go to Dashboard </button>  
    </div>
  </section>
  

  <section id="homeMainSection">
    <div class="mainSectionWrapper sectionWrapper">
      <div id="mainSectionText">
        <h1 id="homeTitle"> Serial Studier v.03 </h1>
        <p id="tagLineHome"> Your one-stop shop for automating your study habits!</p>
        <button id="createAccountButton" @click="goToLogin"> Create Account </button>
      </div>
      <div class="logoContainer">
          <img id="logo" :src="logo" width="250" height="250"/>
      </div>
    </div>
  </section>

  <section class="featuresSection">
    <div class="sectionWrapper">
      <h2 class="featuresTagline"> Studying is hard, takes time, and must be consistent. Imagine, a system that helps you define what you need to learn and when to study it. Automate your study habits with Serial Studier!</h2>
      <div class="featuresRow">
        <div class="featuresEntry">
          <h3> Scheduler </h3>
          <div class="featureImageContainer">
            <img :src="learning_map" class="featureImage" />
          </div>
          <p> Study sessions are automatically scheduled for you. Follow your schedule and adjust day by day with rescheduling or session deletions. </p>
        </div>
        <div class="featuresEntry">
          <h3> Learning Map </h3>
          <div class="featureImageContainer">
            <img :src="scheduler" class="featureImage" />
          </div>
          <p> Map out your learning journey. Pick subjects and topics within each subject. Create questions, reference material, and more! </p>
        </div>
        <div class="featuresEntry">
          <h3> Study Mode </h3>
          <div class="featureImageContainer">
            <img :src="study_mode" class="featureImage" />
          </div>
          <p> Enter into a timed study session. Give feedback about progress and effectiveness of each session to teach the platform more about you! </p>
        </div>
      </div>
    </div>
  </section>
  
  <section id="testimonialSection">
  <div class="testimonial-container">
    <div class="testimonial">
      <p class="testimonial-text">{{ currentTestimonial.text }}</p>
      <h3 class="testimonial-author">{{ currentTestimonial.author }}</h3>
      <div class="testimonialImageContainer">
        <img :src="imageSrc" alt="Testimonial Image" width="250px" height="250px">
      </div>
    </div>

      <div class="testimonial-navigation">
        <button @click="prevTestimonial" class="buttonTestimonial">←</button>
        <button @click="nextTestimonial" class="buttonTestimonial">→</button>
      </div>
    </div>
  </section>

  <section 
    v-if="account.status" id="loginContainerHome">
    <h3 id="h3Home"> You are logged in! Go to the platform </h3>
    <div id="homeButtonContainer">
      <button v-show="loggedIn" @click="goDashboard"> Go to Dashboard </button>  
    </div>
  </section>

  <section 
    v-if="!account.status" id="loginContainerHome">
    <h3 id="h3Home"> You need to login! </h3>
    <div id="homeButtonContainer">
      <button @click="goToLogin"> Go to Login </button>  
    </div>
  </section>

  <!-- <section v-if="!account.status" id="loginContainerHome">
    <h3 id="h3Home"> You need to login! </h3>
    <div id="homeButtonContainer">
      <button @click="goToLogin"> Go to Login Page </button>  
    </div>
  </section> -->

  <FooterComponent />
</template>

<script setup>
import logo from '@/assets/brain_logo.png';
import learning_map from '@/assets/learning_map.png';
import scheduler from "@/assets/scheduler.png";
import study_mode from "@/assets/study_mode.png";
import FooterComponent from '@/components/FooterComponent.vue';

import { useAccountStore } from '@/stores/account';
import { computed } from "vue";
import { useRouter } from "vue-router";
import { ref } from 'vue';

const account = useAccountStore();
const router = useRouter();

const loggedIn = computed(() => account.status);

const goDashboard = () => 
{
  router.push('/platform/dashboard');
}

const goToLogin = () =>
{
  router.push('/login');
}

// Testimonial data
const testimonials = [
  {
    text: "This is an amazing product! It has completely changed my life.",
    author: "John",
    imageUrl: require('@/assets/man1.jpg'),
  },
  {
    text: "I can't imagine going back to the way things were before. Highly recommended!",
    author: "Jane",
    imageUrl: require('@/assets/woman2.jpg'),
  },
  {
    text: "Fantastic service, I'm really happy with the results!",
    author: "Carla",
    imageUrl: require('@/assets/woman3.jpg'),
  },
];

const currentIndex = ref(0);

const currentTestimonial = computed(() => testimonials[currentIndex.value]);

const prevTestimonial = () => {
  if (currentIndex.value > 0) {
    currentIndex.value--;
  }
  else if (currentIndex.value <= 0)
  {
    currentIndex.value = 2;
  }

  console.log(currentIndex.value);
};

const nextTestimonial = () => {
  if (currentIndex.value < testimonials.length - 1) {
    currentIndex.value++;
  }
  else if (currentIndex.value == testimonials.length - 1)
  {
    currentIndex.value = 0;
  }
};

// Computed property for image src
const imageSrc = computed(() => currentTestimonial.value.imageUrl);
</script>

<style>
html 
{
  margin: 0;
  padding: 0;
}

body 
{
  margin: 0;
  padding: 0;
}

/* Main Section */
#homeMainSection {
  background-color: #2c2e3a;
  color: #d3d4dd;
  text-align: center;

  /* Adding a gradient over the image */
  background-image: 
    linear-gradient(
      to bottom, /* Gradient flows from top to bottom */
      rgba(0, 0, 0, 0.9) 0%, /* Darker at the top */
      rgba(0, 0, 0, 0.7) 50%, /* Semi-transparent at the middle */
      rgba(0, 0, 0, 0.9) 100% /* Darker at the bottom */
    ),
    url('~@/assets/tag_picture_study.jpg'); /* Background image */

  background-size: cover; /* Ensures the image fills the section */
  background-position: center; /* Centers the image */
  background-repeat: no-repeat; /* Prevents tiling of the image */

  height: 500px;

  display: flex;
  align-items: center;
}


.mainSectionWrapper
{
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.sectionWrapper
{
  width: 900px;
  padding: 2em 0 2em 0;
  margin-left: auto;
  margin-right: auto;
}

#mainSectionText
{
  text-align: left;
  padding: 1em 2em 1em 0em;
}

#homeTitle
{
  /* padding-top: 1em; */
  margin-top: 0;
  font-size: 3em;
}

#logo {
  animation: float 2s ease-in-out infinite, settle 4s ease-out forwards;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0); /* Start and end at original position */
  }
  50% {
    transform: translateY(-10px); /* Move up slightly */
  }
}

@keyframes settle {
  0% {
    opacity: 0; /* Fully transparent initially */
  }
  100% {
    transform: translateY(0); /* Settle in the middle */
    opacity: 1; /* Fully visible */
  }
}

.logoContainer
{
  padding: 2em 0 2em 0;
}

#tagLineHome
{
  font-size: 2em;
}

#loginContainerHome
{
  display: flex;
  flex-direction: row;
  align-items: center;
  background-color: #050a44;
  padding: 1em;
}

#h3Home
{
  color: white;
  margin-top: 0;
  margin-bottom: 0;
  text-align: center;
  font-size: 1em;
  font-style: italic;
  margin-right: 1em;
}

#homeButtonContainer
{
  display: flex;
  justify-content: center;

}

#homeButtonContainer button {
  font-size: 22px;
  height: 50px;
  width: 250px;
  background: linear-gradient(90deg, #4CAF50, #2E7D32); /* Green gradient */
  color: white; /* Text color */
  border: none; /* Remove border */
  border-radius: 8px; /* Rounded corners */
  cursor: pointer; /* Pointer cursor on hover */
  transition: background 0.3s ease, transform 0.2s ease; /* Smooth transitions */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
}

#homeButtonContainer button:hover {
  background: linear-gradient(90deg, #66BB6A, #388E3C); /* Lighter green on hover */

}

#homeButtonContainer button:active {
  background: linear-gradient(90deg, #388E3C, #2E7D32); /* Darker green on click */
  transform: translateY(0); /* Reset lift on click */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Adjust shadow */
}

#createAccountButton {
  font-size: 22px;
  height: 50px;
  width: 250px;
  background: linear-gradient(90deg, #4CAF50, #2E7D32); /* Green gradient */
  color: white; /* Text color */
  border: none; /* Remove border */
  border-radius: 8px; /* Rounded corners */
  cursor: pointer; /* Pointer cursor on hover */
  transition: background 0.3s ease, transform 0.2s ease; /* Smooth transitions */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
}

#createAccountButton:hover {
  background: linear-gradient(90deg, #66BB6A, #388E3C); /* Lighter green on hover */

}

#homeButtonContainer:active {
  background: linear-gradient(90deg, #388E3C, #2E7D32); /* Darker green on click */
  transform: translateY(0); /* Reset lift on click */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Adjust shadow */
}

/* Features */

.featuresSection
{
  background-color: #e5e6eb;
}

.featuresRow 
{
  display: flex;
  justify-content: center;
  flex-direction: row;
  width: 100%;
}

.featuresEntry
{
  text-align: center;
  padding: 1em;
}

.featureImage
{
  background-color: #050a44;
  width: 100%;
}

.featureImageContainer
{
  width: 300px;
}

/* Testimonials */
#testimonialSection {
  text-align: center;
  padding: 2em;
  background-color: #f4f4f4;
}

.testimonial-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 1em;
  background-color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

.testimonial {
  margin-bottom: 2em;
}

.testimonialImageContainer
{
  border-radius: 100px;
}

.testimonial-text {
  font-size: 1.2em;
  color: #333;
  font-style: italic;
}

.testimonial-author {
  font-size: 1.1em;
  color: #007BFF;
  font-weight: bold;
}

.testimonial-navigation button {
  padding: 0.5em 1em;
  font-size: 1.5em;
  cursor: pointer;
  background-color: transparent;
  border: none;
  color: #007BFF;
  transition: color 0.3s;
}

.testimonial-navigation button:hover {
  color: #3c9426;
}

.testimonialImageContainer img {
  border-radius: 50%;  /* Apply border-radius directly to the image */
  width: 150px;        /* Ensure the image is a square */
  height: 150px;       /* Maintain the circular shape */
  object-fit: cover;   /* Ensure the image covers the area without distortion */
}
</style>
  