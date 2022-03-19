<template>
  <div class="loader">Loading...</div>
</template>

<script setup></script>

<style lang="scss">
// adjust size here.
$i: 0.5;
// https://github.com/lukehaas/css-loaders (MIT Licence)
.loader,
.loader:before,
.loader:after {
  background: $color_secondary;
  -webkit-animation: load1 1s infinite ease-in-out;
  animation: load1 1s infinite ease-in-out;
  width: $i * 1em;
  height: $i * 4em;
}
.loader {
  color: $color_secondary;
  text-indent: -9999em;
  position: relative;
  font-size: 11px;
  -webkit-transform: translateZ(0);
  -ms-transform: translateZ(0);
  transform: translateZ(0);
  -webkit-animation-delay: -0.16s;
  animation-delay: -0.16s;
}
.loader:before,
.loader:after {
  position: absolute;
  top: 0;
  content: '';
}
.loader:before {
  left: $i * -1.5em;
  -webkit-animation-delay: -0.32s;
  animation-delay: -0.32s;
}
.loader:after {
  left: $i * 1.5em;
}
@-webkit-keyframes load1 {
  0%,
  80%,
  100% {
    box-shadow: 0 0;
    height: $i * 4em;
  }
  40% {
    box-shadow: 0 ($i * -2em);
    height: $i * 5em;
  }
}
@keyframes load1 {
  0%,
  80%,
  100% {
    box-shadow: 0 0;
    height: $i * 4em;
  }
  40% {
    box-shadow: 0 ($i * -2em);
    height: $i * 5em;
  }
}
</style>
