<template>
	<div class="grid-scrollable" :style="style">
		<slot>
			<Grid :data-list="[]" :column-labels="{}" />
		</slot>
	</div>
</template>

<script>
import Grid from './Grid.vue'
export default {
	components: { Grid },
	props: {
		height: {
			type: Number,
			required: true
		},
		width: {
			type: Number,
			required: false,
			default: 0
		}
	},
	data() {
		return {
			style: {
				height: this.height + 'px',
				'overflow-y': 'scroll',
				width: this.width > 0 ? this.width + 'px' : '100%'
			}
		}
	}
}
</script>

<style lang="scss">
.grid-scrollable {
	.grid {
		th {
			position: sticky;
			top: 0;
		}
	}
}
</style>
