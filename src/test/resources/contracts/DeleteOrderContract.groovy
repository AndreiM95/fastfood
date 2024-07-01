org.springframework.cloud.contract.spec.Contract.make {
    label 'order_deleted'
    input {
        triggeredBy('triggerDeleteOrder()')
    }
    outputMessage {
        sentTo('orderTopic')
        body([
                event: 'order_deleted',
                details: 'Deleted order with ID: 1'
        ])
        headers {
            messagingContentType(applicationJson())
        }
    }
}
