package com.showka.multitenant_sample.system.bean

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class BeanUtil : ApplicationContextAware {

	@Throws(BeansException::class)
	override fun setApplicationContext(applicationContext: ApplicationContext) {
		context = applicationContext
	}

	companion object {

		private var context: ApplicationContext? = null

		/**
		 * Use this method to manually autowire Spring Beans into classes that are not managed by Spring.
		 *
		 * @param beanClass - Class type of the required bean.
		 */
		fun <T> getBean(beanClass: Class<T>): T {
			return context!!.getBean(beanClass)
		}
	}

}
