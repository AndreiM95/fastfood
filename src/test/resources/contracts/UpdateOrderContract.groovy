org.springframework.cloud.contract.spec.Contract.make {
    label 'order_updated'
    input {
        triggeredBy('triggerUpdateOrder()')
    }
    outputMessage {
        sentTo('orderTopic')
        body([
                event: 'order_updated',
                details: 'Updated order: Burger'
        ])
        headers {
            messagingContentType(applicationJson())
        }
    }
}
