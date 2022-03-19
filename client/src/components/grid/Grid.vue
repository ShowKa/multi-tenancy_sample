<template>
  <table class="grid">
    <thead class="grid__thead">
      <tr>
        <slot name="caption"></slot>
      </tr>
      <tr>
        <th
          v-for="col in columns"
          :key="col.key"
          class="grid__th"
          :class="{
            'grid__th-active': sortKey == col.key,
            'grid__th-sortable': sortableColumns.includes(col.key)
          }"
          @click="sortBy(col.key)"
        >
          {{ col.label }}
          <span
            class="grid__arrow"
            :class="{
              'grid__arrow-asc': sortOrders[col.key] > 0,
              'grid__arrow-dsc': sortOrders[col.key] <= 0,
              'grid__arrow-hidden': sortKey !== col.key
            }"
          >
          </span>
        </th>
        <th v-show="selectable" class="grid__th grid__checkbox">
          <input
            v-model="allSelected"
            type="checkbox"
            class="grid__checkbox__input"
          />
          <label class="grid__checkbox__label">
            <!-- 全選択 -->
          </label>
        </th>
      </tr>
    </thead>
    <transition-group name="grid__tbody" tag="tbody">
      <tr
        v-for="(entry, index) in filteredRows"
        :key="entry._gridRowId"
        class="grid__row"
      >
        <td v-for="col in columns" :key="col.key" class="grid__td">
          <slot
            name="body"
            :index="entry._gridRowIndex"
            :colKey="col.key"
            :row="entry"
            :data="
              formatters[col.key] && entry[col.key]
                ? formatters[col.key](entry[col.key])
                : entry[col.key]
            "
          >
            {{
              formatters[col.key] && entry[col.key]
                ? formatters[col.key](entry[col.key])
                : entry[col.key]
            }}
          </slot>
        </td>
        <td v-show="selectable" class="grid__checkbox">
          <input
            v-model="entry._gridRowSelected"
            type="checkbox"
            class="grid__checkbox__input"
            :disabled="!entry._gridRowSelectable"
            @change="$set(filteredRows, index, entry)"
          />
        </td>
      </tr>
    </transition-group>
  </table>
</template>

<script>
export default {
  props: {
    dataList: { type: Array, required: true },
    columnLabels: { type: Object, required: true },
    filterKey: { type: String, required: false, default: '' },
    selectable: { type: Boolean, required: false, default: false },
    formatters: {
      type: Object,
      required: false,
      default() {
        return {}
      }
    },
    sortableColumns: { type: Array, required: false, default: () => [] }
  },
  data() {
    // columns
    const columns = []
    for (const [key, value] of Object.entries(this.columnLabels)) {
      columns.push({
        key: key,
        label: value
      })
    }
    // sord orders
    var sortOrders = {}
    columns.forEach(function(col) {
      sortOrders[col.key] = 1
    })
    return {
      sortKey: '',
      sortOrders: sortOrders,
      columns: columns
    }
  },
  computed: {
    rows() {
      var i = 0
      this.dataList.forEach(d => {
        d['_gridRowIndex'] = i++
      })
      this.dataList
        .filter(d => !('_gridRowId' in d))
        .forEach(d => {
          d._gridRowId = Math.random()
            .toString(36)
            .substring(2, 9)
        })
      this.dataList
        .filter(d => !('_gridRowSelected' in d))
        .forEach(d => (d._gridRowSelected = false))
      this.dataList
        .filter(d => !('_gridRowSelectable' in d))
        .forEach(d => (d._gridRowSelectable = true))
      return this.dataList
    },
    filteredRows() {
      const sortKey = this.sortKey
      const filterKey = this.filterKey && this.filterKey.toLowerCase()
      const order = this.sortOrders[sortKey] || 1
      let rows = this.rows
      if (filterKey) {
        rows = rows.filter(function(row) {
          return Object.keys(row).some(function(key) {
            return (
              String(row[key])
                .toLowerCase()
                .indexOf(filterKey) > -1
            )
          })
        })
      }
      if (sortKey) {
        rows = rows.slice().sort(function(a, b) {
          a = a[sortKey]
          b = b[sortKey]
          return (a === b ? 0 : a > b ? 1 : -1) * order
        })
      }
      return rows
    },
    allSelected: {
      get() {
        if (this.filteredRows.length === 0) {
          return false
        }
        const unselected = this.filteredRows.findIndex(
          r => r._gridRowSelected === false
        )
        return unselected === -1
      },
      set(newValue) {
        this.filteredRows.forEach((row, index) => {
          // filter api does not work well
          if (!row._gridRowSelectable) {
            return
          }
          row._gridRowSelected = newValue
          // notify updating to Vue in order to re-render forcefully
          this.$set(this.filteredRows, index, row)
        })
      }
    }
  },
  methods: {
    sortBy(key) {
      if (this.sortableColumns.includes(key)) {
        this.sortKey = key
        this.sortOrders[key] = this.sortOrders[key] * -1
      }
    },
    getSelected() {
      return this.rows.filter(row => row._gridRowSelected)
    },
    setSelectable(targetRow, newValue) {
      this._setRowValue(targetRow, newValue, '_gridRowSelectable')
    },
    selectRow(targetRow, newValue) {
      this._setRowValue(targetRow, newValue, '_gridRowSelected')
    },
    _setRowValue(targetRow, newValue, key) {
      const index = this.filteredRows.findIndex(
        row => row._gridRowId === targetRow._gridRowId
      )
      targetRow[key] = newValue
      this.$set(this.filteredRows, index, targetRow)
    }
  }
}
</script>

<style lang="scss">
.grid {
  &__thead {
    border-bottom: $border_strong;
  }
  &__th {
    @extend %text_label;
    font-size: 13px;
    white-space: nowrap;
    background-color: $color_background;
    &-sortable {
      cursor: pointer;
    }
  }
  &__th-active &__arrow {
    opacity: 1;
  }
  &__tbody {
    &-move {
      transition: transform 0.8s ease;
    }
  }
  &__row {
    border-bottom: $border;
  }
  &__td {
    @extend %text_data;
  }
  &__th,
  &__td {
    min-width: 30px;
    padding: 10px 20px;
  }
  &__arrow {
    display: inline-block;
    vertical-align: middle;
    width: 0;
    height: 0;
    margin-left: 5px;
    opacity: 0.66;
    &-asc {
      border-bottom: 4px solid $color_shadow;
    }
    &-dsc {
      border-top: 4px solid $color_shadow;
    }
    &-asc,
    &-dsc {
      border-left: 4px solid transparent;
      border-right: 4px solid transparent;
    }
    &-hidden {
      opacity: 0;
    }
  }
  &__checkbox {
    text-align: center;
    margin-top: 10px;
    margin-bottom: 10px;
    &__input {
      margin-top: 10px;
    }
    &__label {
      @extend %text_label;
      font-size: 11px;
      margin-left: 5px;
    }
  }
}
</style>
