org.springframework.cloud.contract.spec.Contract.make {
    label 'order_created'
    input {
        triggeredBy('triggerCreateOrder()')
    }
    outputMessage {
        sentTo('orderTopic')
        body([
                event: 'order_created',
                details: 'Created order: Burger'
        ])
        headers {
            messagingContentType(applicationJson())
        }
    }
}
